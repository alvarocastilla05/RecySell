package com.example.Recysell.producto.dto;

import com.example.Recysell.producto.model.Estado;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public record EditProductoCmd(
        @NotBlank(message = "El nombre del producto es obligatorio")
        String nombre,

        @NotBlank(message = "La descripción es obligatoria")
        @Size(max = 500, message = "La descripción no puede superar los 500 caracteres")
        String descripcion,

        @Positive(message = "El precio debe ser un valor positivo")
        double precio,

        @NotNull(message = "El estado del producto es obligatorio")
        Estado estado,

        @NotNull(message = "La categoría es obligatoria")
        Long categoriaId
) {
}
