package com.example.Recysell.categoria.dto;

import jakarta.validation.constraints.NotBlank;

public record EditarCategoriaCmd(
        @NotBlank(message = "El nombre es requerido")
        String nombre
) {
}
