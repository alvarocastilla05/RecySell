package com.example.Recysell.compra.dto;

import com.example.Recysell.cliente.dto.GetClienteDto;
import com.example.Recysell.cliente.model.Cliente;
import com.example.Recysell.compra.model.Compra;

import java.time.LocalDateTime;

public record GetCompraDto(
        double gastosEnvio,
        double subTotal,
        LocalDateTime fechaVenta,
        String provincia,
        String codigoPostal,
        String direccionEntrega,
        GetClienteDto clienteDto
) {

    public static GetCompraDto of(Compra compra) {
        return new GetCompraDto(
                compra.getGastosEnvio(),
                compra.getSubTotal(),
                compra.getFechaVenta(),
                compra.getProvincia(),
                compra.getCodigoPostal(),
                compra.getDireccionEntrega(),
                GetClienteDto.of(compra.getCliente())
        );
    }

    public GetCompraDto(
            double gastosEnvio,
            double subTotal,
            LocalDateTime fechaVenta,
            String provincia,
            String codigoPostal,
            String direccionEntrega,
            Cliente cliente
    ) {
        this(
                gastosEnvio,
                subTotal,
                fechaVenta,
                provincia,
                codigoPostal,
                direccionEntrega,
                GetClienteDto.of(cliente)
        );
    }
}
