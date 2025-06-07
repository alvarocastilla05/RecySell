package com.example.Recysell.lineaVenta.service;

import com.example.Recysell.cliente.model.Cliente;
import com.example.Recysell.compra.model.Compra;
import com.example.Recysell.compra.repo.CompraRepository;
import com.example.Recysell.error.CompraNotFoundException;
import com.example.Recysell.error.LineaVentaNotFoundException;
import com.example.Recysell.error.ProductoNotFoundException;
import com.example.Recysell.files.service.StorageService;
import com.example.Recysell.lineaVenta.dto.CreateLineaVentaDto;
import com.example.Recysell.lineaVenta.dto.GetLineaVentaDto;
import com.example.Recysell.lineaVenta.model.LineaVenta;
import com.example.Recysell.lineaVenta.repo.LineaVentaRepository;
import com.example.Recysell.producto.model.Producto;
import com.example.Recysell.producto.repo.ProductoRepository;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.hibernate.Filter;
import org.hibernate.Session;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LineaVentaService {

    private final LineaVentaRepository lineaVentaRepository;
    private final EntityManager entityManager;
    private final StorageService storageService;
    private final CompraRepository compraRepository;
    private final ProductoRepository productoRepository;

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

    // Crear Línea de Venta
    public LineaVenta save(CreateLineaVentaDto nuevo) {

        Compra compra = compraRepository.findById(nuevo.compraId())
                .orElseThrow(() -> new CompraNotFoundException(nuevo.compraId()));

        Producto producto = productoRepository.findById(nuevo.productoId())
                .orElseThrow(() -> new ProductoNotFoundException(nuevo.productoId()));

        LineaVenta lineaVenta = LineaVenta.builder()
                .compra(compra)
                .productoLinea(producto)
                .build();

        return lineaVentaRepository.save(lineaVenta);
    }



}
