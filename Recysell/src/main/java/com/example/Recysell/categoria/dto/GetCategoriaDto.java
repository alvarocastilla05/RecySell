package com.example.Recysell.categoria.dto;

import com.example.Recysell.categoria.model.Categoria;

public record GetCategoriaDto(
        Long id,
        String nombre,
        String imagen
) {

    public static GetCategoriaDto of(Categoria categoria) {
        return new GetCategoriaDto(
                categoria.getId(),
                categoria.getNombre(),
                categoria.getImagen()
        );
    }

}
