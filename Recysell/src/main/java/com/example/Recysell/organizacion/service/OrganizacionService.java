package com.example.Recysell.organizacion.service;

import com.example.Recysell.error.OrganizacionNotFoundException;
import com.example.Recysell.organizacion.dto.EditOrganizacionCmd;
import com.example.Recysell.organizacion.dto.GetOrganizacionDto;
import com.example.Recysell.organizacion.model.Organizacion;
import com.example.Recysell.organizacion.repo.OrganizacionRepository;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.hibernate.Filter;
import org.hibernate.Session;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrganizacionService {

    private final OrganizacionRepository organizacionRepository;
    private final EntityManager entityManager;

    //Listar Organizaciones
    public Page<GetOrganizacionDto> findAll(Pageable pageable, boolean isDeleted){
        Session session = entityManager.unwrap(Session.class);
        Filter filter = session.enableFilter("deletedOrganizacionFilter");
        filter.setParameter("isDeleted", isDeleted);

        Page<GetOrganizacionDto> result = organizacionRepository.findAllOrganizaciones(pageable);
        session.disableFilter("deletedOrganizacionFilter");

        if(result.isEmpty()){
            throw new OrganizacionNotFoundException();
        }

        return result;
    }

    //Obtener Organización por id
    public Organizacion findById(Long id){
        Optional<Organizacion> organizacion = organizacionRepository.findById(id);

        if (organizacion.isPresent() && !organizacion.get().isDeleted()){
            return organizacion.get();
        }else{
            throw new OrganizacionNotFoundException(id);
        }
    }

    //Añadir Organización
    public Organizacion save(EditOrganizacionCmd nuevo){
        return organizacionRepository.save(Organizacion.builder()
                .nombre(nuevo.nombre())
                .direccion(nuevo.direccion())
                .build());
    }
}
