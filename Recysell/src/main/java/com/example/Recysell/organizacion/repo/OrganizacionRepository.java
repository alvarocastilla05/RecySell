package com.example.Recysell.organizacion.repo;

import com.example.Recysell.organizacion.dto.GetOrganizacionDto;
import com.example.Recysell.organizacion.model.Organizacion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface OrganizacionRepository extends JpaRepository<Organizacion, Long> {

    @Query("""
        SELECT new com.example.Recysell.organizacion.dto.GetOrganizacionDto(
            o.id,
            o.nombre,
            o.descripcion,
            o.direccion
    )
    FROM Organizacion o
    """)
    Page<GetOrganizacionDto> findAllOrganizaciones(Pageable pageable);
}
