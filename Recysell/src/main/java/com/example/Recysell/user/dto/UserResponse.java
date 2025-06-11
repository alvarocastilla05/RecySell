package com.example.Recysell.user.dto;

import com.example.Recysell.user.model.User;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.UUID;

public record UserResponse(
        UUID id,
        String username,
        @JsonInclude(JsonInclude.Include.NON_NULL)
        String token,
        @JsonInclude(JsonInclude.Include.NON_NULL)
        String refreshToken,
        String role // <-- Añade este campo
) {

    public static UserResponse of(User user){
        // Suponiendo que solo hay un rol principal
        String mainRole = user.getRoles().stream().findFirst().map(Enum::name).orElse("CLIENTE");
        return new UserResponse(user.getId(), user.getUsername(), null, null, mainRole);
    }

    public static UserResponse of(User user, String token, String refreshToken){
        String mainRole = user.getRoles().stream().findFirst().map(Enum::name).orElse("CLIENTE");
        return new UserResponse(user.getId(), user.getUsername(), token, refreshToken, mainRole);
    }
}
