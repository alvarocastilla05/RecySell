package com.example.Recysell.categoria.service;

import com.example.Recysell.categoria.dto.EditarCategoriaCmd;
import com.example.Recysell.categoria.dto.GetCategoriaDto;
import com.example.Recysell.categoria.model.Categoria;
import com.example.Recysell.categoria.repo.CategoriaRepository;
import com.example.Recysell.error.CategoriaNotFoundException;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.hibernate.Filter;
import org.hibernate.Session;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;
    private final EntityManager entityManager;


    //Listar Categorias.
    public Page<GetCategoriaDto> findAll(Pageable pageable, boolean isDeleted){
        Session session = entityManager.unwrap(Session.class);
        Filter filter = session.enableFilter("deletedCategoriaFilter");
        filter.setParameter("isDeleted", isDeleted);

        Page<GetCategoriaDto> result = categoriaRepository.findAllCategorias(pageable);
        session.disableFilter("deletedCategoriaFilter");

        if(result.isEmpty()){
            throw new CategoriaNotFoundException();
        }
        return result;
    }

    //Añadir categoria.
    public Categoria save(EditarCategoriaCmd nuevo){
        return categoriaRepository.save(Categoria.builder()
                .nombre(nuevo.nombre())
                .build());
    }
}
