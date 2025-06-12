package com.example.Recysell.cliente.dto;

import com.example.Recysell.cliente.model.Cliente;

import java.util.UUID;

public record GetClienteDto(
        UUID id,
        String username,
        String email,
        String nombre,
        String apellidos
) {

    public static GetClienteDto of(Cliente cliente) {
        if (cliente == null) {
            return new GetClienteDto(null, null, null, null, null); // Retorna un DTO con valores nulos
        }
        return new GetClienteDto(
                cliente.getId(),
                cliente.getUsername(),
                cliente.getEmail(),
                cliente.getNombre(),
                cliente.getApellidos()
        );
    }
}
