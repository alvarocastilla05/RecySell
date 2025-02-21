package com.example.Recysell.trabajador.dto;

import com.example.Recysell.trabajador.model.Trabajador;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.UUID;

public record TrabajadorResponse(
        UUID id,
        String username,
        @JsonInclude(JsonInclude.Include.NON_NULL)
        String token,
        @JsonInclude(JsonInclude.Include.NON_NULL)
        String refreshToken
) {

    public static TrabajadorResponse of(Trabajador trabajador) {
        return new TrabajadorResponse(trabajador.getId(), trabajador.getUsername(), null, null);
    }

    public static TrabajadorResponse of(Trabajador trabajador, String token, String refreshToken) {
        return new TrabajadorResponse(trabajador.getId(), trabajador.getUsername(), token, refreshToken);
    }
}
