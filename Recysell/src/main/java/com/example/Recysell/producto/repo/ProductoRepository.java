package com.example.Recysell.producto.repo;

import com.example.Recysell.categoria.dto.GetCategoriaDto;
import com.example.Recysell.cliente.model.Cliente;
import com.example.Recysell.producto.dto.GetProductoDto;
import com.example.Recysell.producto.model.Producto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface ProductoRepository extends JpaRepository<Producto, Long>, JpaSpecificationExecutor<Producto> {


    Page<Producto> findByClienteVendedorIsNotNull(Pageable pageable);

    boolean existsByIdAndClienteVendedor_Username(Long productoId, String username);

    Page<Producto> findProductosByClienteVendedor(Cliente cliente, Pageable pageable);

    @Query("""
    SELECT new com.example.Recysell.categoria.dto.GetCategoriaDto(c.id, c.nombre, c.imagen)
    FROM Categoria c
    JOIN c.listaProductos p 
    WHERE c.id = :id
""")
    Set<GetProductoDto> findProductosByCategoria(@Param("id") Long id);
}
