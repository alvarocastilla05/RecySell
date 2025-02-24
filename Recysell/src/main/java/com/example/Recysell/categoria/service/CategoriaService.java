package com.example.Recysell.categoria.service;

import com.example.Recysell.categoria.dto.EditarCategoriaCmd;
import com.example.Recysell.categoria.model.Categoria;
import com.example.Recysell.categoria.repo.CategoriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    //Añadir categoria.
    public Categoria save(EditarCategoriaCmd nuevo){
        return categoriaRepository.save(Categoria.builder()
                .nombre(nuevo.nombre())
                .build());
    }
}
