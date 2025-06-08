package com.example.Recysell.lineaVenta.repo;

import com.example.Recysell.cliente.model.Cliente;
import com.example.Recysell.compra.model.Compra;
import com.example.Recysell.compra.model.EstadoCompra;
import com.example.Recysell.lineaVenta.dto.GetLineaVentaDto;
import com.example.Recysell.lineaVenta.model.LineaVenta;
import com.example.Recysell.producto.model.Producto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface LineaVentaRepository extends JpaRepository<LineaVenta, Long> {

    @Query("""
        SELECT new com.example.Recysell.lineaVenta.dto.GetLineaVentaDto(
            lv.productoLinea, lv.compra
        )
        FROM LineaVenta lv
    """)
    Page<GetLineaVentaDto> findAllLineasVenta(Pageable pageable);

    Optional<LineaVenta> findByCompraAndProductoLinea(Compra compra, Producto producto);

    List<LineaVenta> findByProductoLineaAndCompraEstadoCompraAndCompraClienteNot(
            Producto producto, EstadoCompra estadoCompra, Cliente cliente);

}
