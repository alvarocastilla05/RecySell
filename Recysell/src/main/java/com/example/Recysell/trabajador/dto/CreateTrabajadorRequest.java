package com.example.Recysell.trabajador.dto;

import javax.management.relation.Role;

public record CreateTrabajadorRequest(
        String username,
        String email,
        String password,
        String verifyPassword,
        Role role,
        String nombre,
        String apellido,
        String puesto
) {
}
