package com.example.Recysell.cliente.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record EditClienteCmd(
        @NotBlank(message = "El nombre de usuario no puede estar vacío.")
        String username,

        @NotBlank(message = "La contraseña no puede estar vacía.")
        @Size(min = 6, message = "La contraseña debe tener al menos 6 caracteres.")
        String password,

        @NotBlank(message = "El correo electrónico no puede estar vacío.")
        @Email(message = "El correo electrónico debe tener un formato válido.")
        String email,

        @NotBlank(message = "El nombre no puede estar vacío.")
        String nombre,

        @NotBlank(message = "Los apellidos no pueden estar vacíos.")
        String apellidos
) {
}
