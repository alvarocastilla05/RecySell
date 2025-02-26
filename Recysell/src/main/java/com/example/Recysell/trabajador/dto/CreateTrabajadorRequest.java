package com.example.Recysell.trabajador.dto;


import com.example.Recysell.security.validation.UniqueUsername;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import com.example.Recysell.user.model.UserRole;


public record CreateTrabajadorRequest(
        @NotBlank(message = "El username no puede estar vacío")
        @UniqueUsername(message = "El username ya está en uso")
        String username,

        @NotBlank(message = "El email no puede estar vacío")
        @Email(message = "El email debe ser válido")
        String email,

        @NotBlank(message = "La contraseña no puede estar vacía")
        @Size(min = 6, message = "La contraseña debe tener al menos 6 caracteres")
        String password,

        @NotBlank(message = "Debe verificar la contraseña")
        String verifyPassword,

        @NotBlank(message = "El nombre no puede estar vacío")
        String nombre,

        @NotBlank(message = "El apellido no puede estar vacío")
        String apellido,

        @NotBlank(message = "El puesto no puede estar vacío")
        String puesto
) {}
