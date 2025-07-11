package com.example.Recysell.lineaVenta.service;

import com.example.Recysell.cliente.model.Cliente;
import com.example.Recysell.compra.model.Compra;
import com.example.Recysell.compra.repo.CompraRepository;
import com.example.Recysell.error.CompraNotFoundException;
import com.example.Recysell.error.LineaVentaNotFoundException;
import com.example.Recysell.error.ProductoNotFoundException;
import com.example.Recysell.error.ProductoYaEnCarritoException;
import com.example.Recysell.files.service.StorageService;
import com.example.Recysell.lineaVenta.dto.CreateLineaVentaDto;
import com.example.Recysell.lineaVenta.dto.GetLineaVentaDto;
import com.example.Recysell.lineaVenta.model.LineaVenta;
import com.example.Recysell.lineaVenta.repo.LineaVentaRepository;
import com.example.Recysell.producto.model.Producto;
import com.example.Recysell.producto.repo.ProductoRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.hibernate.Filter;
import org.hibernate.Session;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LineaVentaService {

    private final LineaVentaRepository lineaVentaRepository;
    private final EntityManager entityManager;
    private final StorageService storageService;
    private final CompraRepository compraRepository;
    private final ProductoRepository productoRepository;

    public boolean esPropietario(Long lineaVentaId, String username) {

        return lineaVentaRepository.existsByIdAndCompraClienteUsername(lineaVentaId, username);
    }

    // Listar Líneas de Venta
    public Page<GetLineaVentaDto> findAll(Pageable pageable, boolean isDeleted) {
        Session session = entityManager.unwrap(Session.class);
        Filter filter = session.enableFilter("deletedLineaVentaFilter");
        filter.setParameter("isDeleted", isDeleted);

        Page<GetLineaVentaDto> result = lineaVentaRepository.findAllLineasVenta(pageable);
        session.disableFilter("deletedLineaVentaFilter");

        if (result.isEmpty()) {
            throw new LineaVentaNotFoundException();
        }
        return result;
    }

    // Añadir línea de venta
    @Transactional
    public LineaVenta save(CreateLineaVentaDto nuevo) {
        Compra compra = compraRepository.findById(nuevo.compraId())
                .orElseThrow(() -> new CompraNotFoundException(nuevo.compraId()));

        Producto producto = productoRepository.findById(nuevo.productoId())
                .orElseThrow(() -> new ProductoNotFoundException(nuevo.productoId()));

        if(!producto.isDisponibilidad()) {
            throw new ProductoNotFoundException("El producto con ID " + producto.getId() + " no está disponible.");
        }

        boolean existe = lineaVentaRepository.findByCompraAndProductoLinea(compra, producto).isPresent();

        if (existe) {
            throw new ProductoYaEnCarritoException("El producto con ID " + producto.getId() + " ya está en el carrito.");
        }

        LineaVenta nuevaLinea = LineaVenta.builder()
                .compra(compra)
                .productoLinea(producto)
                .build();

        try {
            LineaVenta guardada = lineaVentaRepository.save(nuevaLinea);

            // ✅ Sumar el precio del producto al subtotal de la compra
            compra.setSubTotal(compra.getSubTotal() + producto.getPrecio());
            compraRepository.save(compra);

            return guardada;
        } catch (DataIntegrityViolationException ex) {
            throw new ProductoYaEnCarritoException("El producto con ID " + producto.getId() + " ya está en el carrito.");
        }
    }


    //Eliminar línea de venta
    @Transactional
    public void deleteById(Long id) {
        LineaVenta lineaVenta = lineaVentaRepository.findById(id)
                .orElseThrow(() -> new LineaVentaNotFoundException(id));

        Compra compra = lineaVenta.getCompra();
        if (compra != null) {
            Producto producto = lineaVenta.getProductoLinea();
            compra.setSubTotal(compra.getSubTotal() - producto.getPrecio());

            if (compra.getSubTotal() < 0) {
                compra.setSubTotal(0);
            }


            compra.removeLineaVenta(lineaVenta); // Limpieza bidireccional
            compraRepository.save(compra);
        }

        // Soft delete: marcar como eliminado
        lineaVenta.setDeleted(true);
        lineaVentaRepository.save(lineaVenta); // Guardar el cambio de estado
    }








}
