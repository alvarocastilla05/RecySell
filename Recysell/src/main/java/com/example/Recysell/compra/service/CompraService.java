package com.example.Recysell.compra.service;

import com.example.Recysell.compra.dto.GetCompraDto;
import com.example.Recysell.compra.model.Compra;
import com.example.Recysell.compra.model.EstadoCompra;
import com.example.Recysell.compra.repo.CompraRepository;
import com.example.Recysell.error.CompraNotFoundException;
import com.example.Recysell.lineaVenta.model.LineaVenta;
import com.example.Recysell.lineaVenta.repo.LineaVentaRepository;
import com.example.Recysell.producto.model.Producto;
import com.example.Recysell.producto.repo.ProductoRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.hibernate.Filter;
import org.hibernate.Session;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CompraService {

    private final CompraRepository compraRepository;
    private final ProductoRepository productoRepository;
    private final LineaVentaRepository lineaVentaRepository;
    private final EntityManager entityManager;


    @Transactional
    public void confirmarCompra(Compra compra) {
        for (LineaVenta lv : compra.getLineaVentas()) {
            Producto producto = lv.getProductoLinea();

            // Marcar como no disponible
            producto.setDisponibilidad(false);
            productoRepository.save(producto);

            // Eliminar el producto de carritos de otros clientes
            List<LineaVenta> otrasLineas = lineaVentaRepository
                    .findByProductoLineaAndCompraEstadoCompraAndCompraClienteNot(
                            producto, EstadoCompra.CARRITO, compra.getCliente());

            for (LineaVenta lvAjena : otrasLineas) {
                lineaVentaRepository.delete(lvAjena);
            }
        }
        // Marcar compra como confirmada
        LocalDateTime hoy = LocalDateTime.now();
        compra.setEstadoCompra(EstadoCompra.EN_ENVIO);
        compra.setFechaVenta(hoy);
        compra.setFechaEntrega(hoy.plusDays(15));

        // Guardar cambios en la compra (estado actualizado fuera de este método si es necesario)
        compraRepository.save(compra);
    }

    //Listar Compras.
    public Page<GetCompraDto> findAll(Pageable pageable, boolean isDeleted) {
        Session session = entityManager.unwrap(Session.class);
        Filter filter = session.enableFilter("deletedCompraFilter");
        filter.setParameter("isDeleted", isDeleted);

        Page<GetCompraDto> result = compraRepository.findAllCompras(pageable);
        session.disableFilter("deletedCompraFilter");

        if (result.isEmpty()) {
            throw new CompraNotFoundException();
        }
        return result;
    }
}

