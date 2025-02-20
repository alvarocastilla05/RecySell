package com.example.Recysell.organizacion.repo;

import com.example.Recysell.organizacion.model.Organizacion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrganizacionRepository extends JpaRepository<Organizacion, UUID> {
}
