package com.example.Recysell.categoria.dto;

import com.example.Recysell.categoria.model.Categoria;

public record GetCategoriaDto(
        String nombre
) {

    public static GetCategoriaDto of(Categoria categoria) {
        return new GetCategoriaDto(
                categoria.getNombre()
        );
    }

}
