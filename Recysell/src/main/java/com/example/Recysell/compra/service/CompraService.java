package com.example.Recysell.compra.service;

import com.example.Recysell.cliente.model.Cliente;
import com.example.Recysell.cliente.repo.ClienteRepository;
import com.example.Recysell.compra.dto.CreateCompraDto;
import com.example.Recysell.compra.dto.GetCompraDto;
import com.example.Recysell.compra.model.Compra;
import com.example.Recysell.compra.model.EstadoCompra;
import com.example.Recysell.compra.repo.CompraRepository;
import com.example.Recysell.error.ClienteNotFoundException;
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
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CompraService {

    private final CompraRepository compraRepository;
    private final ProductoRepository productoRepository;
    private final LineaVentaRepository lineaVentaRepository;
    private final ClienteRepository clienteRepository;
    private final EntityManager entityManager;

    public boolean esPropietario(Long compraId, String username) {
        return compraRepository.existsByIdAndCliente_Username(compraId, username);
    }


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
                Compra compraAjena = lvAjena.getCompra();
                if (compraAjena != null) {
                    // Ajustar el subtotal de la compra ajena
                    compraAjena.setSubTotal(compraAjena.getSubTotal() - producto.getPrecio());
                    compraAjena.removeLineaVenta(lvAjena); // Limpieza bidireccional
                    compraRepository.save(compraAjena);
                }

                // Marcar la línea de venta como eliminada (soft delete)
                lvAjena.setDeleted(true);
                lineaVentaRepository.save(lvAjena);
            }
        }

        // Marcar compra como confirmada
        LocalDateTime hoy = LocalDateTime.now();
        compra.setEstadoCompra(EstadoCompra.EN_ENVIO);
        compra.setFechaVenta(hoy);
        compra.setFechaEntrega(hoy.plusDays(15));

        // Guardar cambios en la compra
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

    @Transactional
    public Optional<Compra> findById(Long id) {
        Optional<Compra> compra = compraRepository.findById(id);

        if (compra.isPresent() && !compra.get().isDeleted()) {
            return compra;
        } else {
            throw new CompraNotFoundException(id);
        }
    }

    //Listar Compras por Cliente.
    public Page<GetCompraDto> findCompraByCliente(UUID clienteId, Pageable pageable, boolean isDeleted) {
        Session session = entityManager.unwrap(Session.class);
        Filter filter = session.enableFilter("deletedCompraFilter");
        filter.setParameter("isDeleted", isDeleted);

        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new ClienteNotFoundException(clienteId));

        Page<GetCompraDto> result = compraRepository.findCompraByCliente(cliente, pageable);
        session.disableFilter("deletedCompraFilter");

        if (result.isEmpty()) {
            throw new CompraNotFoundException();
        }
        return result;
    }

    //Crear Compra.
    @Transactional
    public Compra addCompra(CreateCompraDto dto, Cliente cliente) {

        Compra compra = Compra.builder()
                .cliente(cliente)
                .estadoCompra(EstadoCompra.CARRITO)
                .gastosEnvio(5.00)
                .subTotal(0.0) // fijo en 0
                .provincia(dto.provincia())
                .codigoPostal(dto.codigoPostal())
                .direccionEntrega(dto.direccionEntrega())
                .fechaVenta(null)
                .build();

        return compraRepository.save(compra);
    }

    // Editar solo los datos de entrega de una compra
    public Compra editarCompra(Long id, CreateCompraDto edit) {
        Optional<Compra> compra = compraRepository.findById(id);

        if (compra.isPresent() && !compra.get().isDeleted()) {
            return compra
                    .map(c -> {
                        c.setProvincia(edit.provincia());
                        c.setCodigoPostal(edit.codigoPostal());
                        c.setDireccionEntrega(edit.direccionEntrega());
                        return compraRepository.save(c);
                    }).get();
        } else {
            throw new CompraNotFoundException(id);
        }
    }

    private void actualizarSubtotal(Compra compra) {
        double nuevoSubtotal = compra.getLineaVentas().stream()
                .mapToDouble(lv -> lv.getProductoLinea().getPrecio())
                .sum();
        compra.setSubTotal(nuevoSubtotal);
        compraRepository.save(compra);
    }




}

