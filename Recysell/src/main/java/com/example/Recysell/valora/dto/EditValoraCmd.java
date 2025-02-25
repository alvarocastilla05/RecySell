package com.example.Recysell.valora.dto;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record EditValoraCmd(
        @NotNull(message = "La puntuación no puede ser nula")
        @Min(value = 1, message = "La puntuación mínima es 1")
        int puntuacion,

        @Size(max = 500, message = "El comentario no puede exceder los 500 caracteres")
        String comentario
) {
}
