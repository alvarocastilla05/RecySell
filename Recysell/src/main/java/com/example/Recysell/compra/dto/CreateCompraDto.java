package com.example.Recysell.compra.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.util.UUID;

public record CreateCompraDto(

        @NotBlank(message = "La provincia no puede estar vacía")
        String provincia,

        @NotBlank(message = "El código postal no puede estar vacío")
        @Pattern(regexp = "\\d{5}", message = "El código postal debe tener 5 dígitos")
        String codigoPostal,

        @NotBlank(message = "La dirección de entrega no puede estar vacía")
        String direccionEntrega
) {
}
