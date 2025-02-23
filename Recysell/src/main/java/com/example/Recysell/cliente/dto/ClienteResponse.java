package com.example.Recysell.cliente.dto;

import com.example.Recysell.cliente.model.Cliente;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.UUID;

public record ClienteResponse(
        UUID id,
        String username,
        @JsonInclude(JsonInclude.Include.NON_NULL)
        String token,
        @JsonInclude(JsonInclude.Include.NON_NULL)
        String refreshToken
) {

    public static ClienteResponse of(Cliente cliente) {
        return new ClienteResponse(cliente.getId(), cliente.getUsername(), null, null);
    }

    public static ClienteResponse of(Cliente cliente, String token, String refreshToken) {
        return new ClienteResponse(cliente.getId(), cliente.getUsername(), token, refreshToken);
    }
}
