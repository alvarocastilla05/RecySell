package com.example.Recysell.cliente.repo;

import com.example.Recysell.cliente.dto.GetClienteDto;
import com.example.Recysell.cliente.model.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ClienteRepository extends JpaRepository<Cliente, UUID> {

    @Query("""
        SELECT new com.example.Recysell.cliente.dto.GetClienteDto(
        c.username, c.email, c.nombre, c.apellidos
        )
        FROM Cliente c
    """)
    public Page<GetClienteDto> findAllClienteDto(Pageable pageable);

    boolean existsByUsername(String username);

    @Query("SELECT c FROM Cliente c JOIN FETCH c.listProductosEnVenta WHERE c.id = :id")
    Optional<Cliente> findByIdWithProductosEnVenta(@Param("id") UUID id);

}
