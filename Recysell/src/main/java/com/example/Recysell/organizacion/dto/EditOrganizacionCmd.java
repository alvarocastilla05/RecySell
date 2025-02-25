package com.example.Recysell.organizacion.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record EditOrganizacionCmd(
        @NotBlank(message = "El nombre no puede estar vacío")
        @Size(max = 100, message = "El nombre no puede tener más de 100 caracteres")
        String nombre,

        @NotBlank(message = "La dirección no puede estar vacía")
        @Size(max = 200, message = "La dirección no puede tener más de 200 caracteres")
        String direccion
) {
}
