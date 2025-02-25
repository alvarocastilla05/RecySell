package com.example.Recysell.valora.dto;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public record CreateValoraRequest(
        @Min(value = 1, message = "La puntuación debe ser al menos 1")
        @NotNull(message = "La puntuación no puede ser nula")
        int puntuacion,

        @Size(max = 500, message = "El comentario no puede exceder los 500 caracteres")
        String comentario,

        @NotNull(message = "El ID del producto no puede ser nulo")
        Long productoId
) {
}
