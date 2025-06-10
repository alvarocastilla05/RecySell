package com.example.Recysell.lineaVenta.dto;

import jakarta.validation.constraints.NotNull;

public record CreateLineaVentaDto(

        @NotNull(message = "El ID del producto es obligatorio")
        Long productoId,

        @NotNull(message = "El ID de la compra es obligatorio")
        Long compraId
) {
}
