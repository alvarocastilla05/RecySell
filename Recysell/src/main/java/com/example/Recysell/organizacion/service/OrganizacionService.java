package com.example.Recysell.organizacion.service;

import com.example.Recysell.organizacion.dto.EditOrganizacionCmd;
import com.example.Recysell.organizacion.model.Organizacion;
import com.example.Recysell.organizacion.repo.OrganizacionRepository;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrganizacionService {

    private final OrganizacionRepository organizacionRepository;
    private final EntityManager entityManager;

    //Añadir Organización
    public Organizacion save(EditOrganizacionCmd nuevo){
        return organizacionRepository.save(Organizacion.builder()
                .nombre(nuevo.nombre())
                .direccion(nuevo.direccion())
                .build());
    }
}
