package com.example.Recysell.producto.dto;

import com.example.Recysell.cliente.dto.GetClienteDto;
import jakarta.validation.constraints.*;

import java.util.UUID;

public record CreateProductoDto(
        @NotBlank(message = "El nombre del producto es obligatorio")
        String nombre,

        @NotBlank(message = "La descripción es obligatoria")
        @Size(max = 500, message = "La descripción no puede superar los 500 caracteres")
        String descripcion,

        @Positive(message = "El precio debe ser un valor positivo")
        double precio
) {
}

