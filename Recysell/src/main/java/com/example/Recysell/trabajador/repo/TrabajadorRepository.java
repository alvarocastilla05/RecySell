package com.example.Recysell.trabajador.repo;

import com.example.Recysell.trabajador.dto.GetTrabajadorDto;
import com.example.Recysell.trabajador.model.Trabajador;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface TrabajadorRepository extends JpaRepository<Trabajador, UUID> {

    @Query("""
        SELECT new com.example.Recysell.trabajador.dto.GetTrabajadorDto(
        t.id, t.username, t.email, t.nombre, t.apellidos, t.puesto
        )
        FROM Trabajador t
    """)
    public Page<GetTrabajadorDto> findAllTrabajadorDto(Pageable pageable);
}
