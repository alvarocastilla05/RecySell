package com.example.Recysell.organizacion.dto;

import com.example.Recysell.organizacion.model.Organizacion;

public record GetOrganizacionDto(
        String nombre,
        String descripcion,
        String direccion
) {

    public static GetOrganizacionDto of(Organizacion organizacion){
        return new GetOrganizacionDto(
                organizacion.getNombre(),
                organizacion.getDescripcion(),
                organizacion.getDireccion()
        );
    }
}
