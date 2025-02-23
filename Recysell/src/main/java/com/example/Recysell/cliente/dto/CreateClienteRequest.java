package com.example.Recysell.cliente.dto;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateClienteRequest(
        @NotBlank(message = "El username no puede estar vacío")
        String username,

        @NotBlank(message = "El email no puede estar vacío")
        @Email(message = "El email debe ser válido")
        String email,

        @NotBlank(message = "El nombre no puede estar vacío")
        String nombre,

        @NotBlank(message = "El apellido no puede estar vacío")
        String apellidos,

        @NotBlank(message = "La contraseña no puede estar vacía")
        @Size(min = 6, message = "La contraseña debe tener al menos 6 caracteres")
        String password,

        @NotBlank(message = "Debe verificar la contraseña")
        String verifyPassword

) {
        @AssertTrue(message = "Las contraseñas no coinciden")
        public boolean isPasswordsMatch() {
                return password != null && password.equals(verifyPassword);
        }
}
