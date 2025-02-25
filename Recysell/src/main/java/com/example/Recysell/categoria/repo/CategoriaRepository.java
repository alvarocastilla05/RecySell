package com.example.Recysell.categoria.repo;

import com.example.Recysell.categoria.dto.GetCategoriaDto;
import com.example.Recysell.categoria.model.Categoria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    @Query("""
    SELECT new com.example.Recysell.categoria.dto.GetCategoriaDto(c.nombre)
    FROM Producto p
    JOIN p.listaCategorias c
    WHERE p.id = :id
""")
    Set<GetCategoriaDto> findCategoriasByProductoId(@Param("id") Long id);


    @Query("""
    SELECT new com.example.Recysell.categoria.dto.GetCategoriaDto(
    c.nombre
    )
    FROM Categoria c
    """)
    Page<GetCategoriaDto> findAllCategorias(Pageable pageable);
}
