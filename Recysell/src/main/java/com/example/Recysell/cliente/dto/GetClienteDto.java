package com.example.Recysell.cliente.dto;

import com.example.Recysell.cliente.model.Cliente;

public record GetClienteDto(
        String username,
        String email,
        String nombre,
        String apellidos
) {

    public static GetClienteDto of(Cliente cliente){
        return new GetClienteDto(
                cliente.getUsername(),
                cliente.getEmail(),
                cliente.getNombre(),
                cliente.getApellidos()
        );
    }
}
