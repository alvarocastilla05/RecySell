package com.example.Recysell.lineaVenta.dto;

import com.example.Recysell.compra.dto.GetCompraDto;
import com.example.Recysell.compra.model.Compra;
import com.example.Recysell.lineaVenta.model.LineaVenta;
import com.example.Recysell.producto.dto.GetProductoDonadoDto;
import com.example.Recysell.producto.dto.GetProductoDto;
import com.example.Recysell.producto.model.Producto;

public record GetLineaVentaDto(

     GetProductoDto productoDto,
     GetCompraDto compraDto
) {
    public static GetLineaVentaDto of(LineaVenta lineaVenta){
        return new GetLineaVentaDto(
                GetProductoDto.of(lineaVenta.getProductoLinea()),
                GetCompraDto.of(lineaVenta.getCompra())
        );
    }

    public GetLineaVentaDto(Producto producto, Compra compra){
        this(GetProductoDto.of(producto), GetCompraDto.of(compra));
    }
}
