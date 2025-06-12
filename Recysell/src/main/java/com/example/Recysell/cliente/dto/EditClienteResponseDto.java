package com.example.Recysell.cliente.dto;

public record EditClienteResponseDto(
        GetClienteDto usuario,
        String token
) {
}
