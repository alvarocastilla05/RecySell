package com.example.Recysell.producto.dto;

import com.example.Recysell.categoria.model.Categoria;
import com.example.Recysell.producto.model.Estado;
import jakarta.validation.constraints.*;

import java.time.LocalDateTime;
import java.util.UUID;

public record CreateProductoDto(
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

