package com.example.Recysell.lineaVenta.dto;

import com.example.Recysell.compra.dto.GetCompraDto;
import com.example.Recysell.compra.model.Compra;
import com.example.Recysell.lineaVenta.model.LineaVenta;
import com.example.Recysell.producto.dto.GetProductoDto;
import com.example.Recysell.producto.model.Producto;

public record GetLineaVentaSinCompraDto(
        Long id,
        GetProductoDto productoDto
) {
    public static GetLineaVentaSinCompraDto of(LineaVenta lineaVenta) {
        return new GetLineaVentaSinCompraDto(
                lineaVenta.getId(),
                GetProductoDto.of(lineaVenta.getProductoLinea())
        );
    }

    public GetLineaVentaSinCompraDto(Long id, Producto producto) {
        this(id, GetProductoDto.of(producto));
    }
}