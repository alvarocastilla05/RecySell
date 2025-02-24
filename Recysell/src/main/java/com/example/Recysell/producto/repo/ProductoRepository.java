package com.example.Recysell.producto.repo;

import com.example.Recysell.producto.dto.GetProductoDto;
import com.example.Recysell.producto.model.Producto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface ProductoRepository extends JpaRepository<Producto, Long> {

    @Query("""
        SELECT new com.example.Recysell.producto.dto.GetProductoDto(
            p.nombre, p.descripcion, p.precio, p.imagen, p.clienteVendedor
        )
        FROM Producto p
        WHERE p.clienteVendedor IS NOT NULL
    """)
    public Page<GetProductoDto> findAllProductosEnVenta(Pageable pageable);


    boolean existsByIdAndClienteVendedor_Username(Long productoId, String username);
}
