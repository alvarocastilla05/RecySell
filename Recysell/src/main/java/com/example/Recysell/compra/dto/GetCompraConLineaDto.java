package com.example.Recysell.compra.dto;

import com.example.Recysell.cliente.dto.GetClienteDto;
import com.example.Recysell.cliente.model.Cliente;
import com.example.Recysell.compra.model.Compra;
import com.example.Recysell.compra.model.EstadoCompra;
import com.example.Recysell.lineaVenta.dto.GetLineaVentaDto;
import com.example.Recysell.lineaVenta.dto.GetLineaVentaSinCompraDto;

import java.time.LocalDateTime;
import java.util.List;

public record GetCompraConLineaDto(
        double gastosEnvio,
        double subTotal,
        LocalDateTime fechaVenta,
        String provincia,
        String codigoPostal,
        String direccionEntrega,
        EstadoCompra estadoCompra,
        GetClienteDto clienteDto,
        List<GetLineaVentaSinCompraDto> lineasVenta
) {

    public static GetCompraConLineaDto of(Compra compra) {
        return new GetCompraConLineaDto(
                compra.getGastosEnvio(),
                compra.getSubTotal(),
                compra.getFechaVenta(),
                compra.getProvincia(),
                compra.getCodigoPostal(),
                compra.getDireccionEntrega(),
                compra.getEstadoCompra(),
                GetClienteDto.of(compra.getCliente()),
                compra.getLineaVentas().stream()
                        .map(GetLineaVentaSinCompraDto::of)
                        .toList()
        );
    }

    public GetCompraConLineaDto(
            double gastosEnvio,
            double subTotal,
            LocalDateTime fechaVenta,
            String provincia,
            String codigoPostal,
            String direccionEntrega,
            EstadoCompra estadoCompra,
            Cliente cliente,
            List<GetLineaVentaSinCompraDto> lineasVenta
    ) {
        this(
                gastosEnvio,
                subTotal,
                fechaVenta,
                provincia,
                codigoPostal,
                direccionEntrega,
                estadoCompra,
                GetClienteDto.of(cliente),
                lineasVenta
        );
    }
}