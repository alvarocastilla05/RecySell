package com.example.Recysell.categoria.dto;

import com.example.Recysell.categoria.model.Categoria;

public record GetCategoriaDto(
        String nombre,
        String imagen
) {

    public static GetCategoriaDto of(Categoria categoria) {
        return new GetCategoriaDto(
                categoria.getNombre(),
                categoria.getImagen()
        );
    }

}
