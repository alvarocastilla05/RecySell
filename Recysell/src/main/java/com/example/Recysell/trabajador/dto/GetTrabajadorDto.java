package com.example.Recysell.trabajador.dto;

import com.example.Recysell.trabajador.model.Trabajador;

import java.util.UUID;

public record GetTrabajadorDto(
        UUID id,
        String username,
        String email,
        String nombre,
        String apellidos,
        String puesto
) {

    public static GetTrabajadorDto of(Trabajador trabajador){
        return new GetTrabajadorDto(
                trabajador.getId(),
                trabajador.getUsername(),
                trabajador.getEmail(),
                trabajador.getNombre(),
                trabajador.getApellidos(),
                trabajador.getPuesto()
        );
    }
}
