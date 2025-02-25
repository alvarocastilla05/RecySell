package com.example.Recysell.donacion.dto;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record EditDonacionCmd(
        @NotNull(message = "La fecha de donación no puede ser nula")
        @FutureOrPresent(message = "La fecha de donación no puede estar en el pasado")
        LocalDateTime fechaDonacion,

        @NotNull(message = "El id del producto no puede ser nulo")
        Long productoId,

        @NotNull(message = "El id de la organización no puede ser nulo")
        Long organizacionId
) {
}
