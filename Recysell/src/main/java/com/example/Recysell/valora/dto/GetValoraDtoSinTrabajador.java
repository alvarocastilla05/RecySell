package com.example.Recysell.valora.dto;

import com.example.Recysell.producto.dto.GetProductoDto;
import com.example.Recysell.producto.model.Producto;
import com.example.Recysell.valora.model.Valora;

public record GetValoraDtoSinTrabajador(
        int puntuacion,
        String comentario,
        GetProductoDto producto
){

    public static GetValoraDtoSinTrabajador of(Valora valora){
        return new GetValoraDtoSinTrabajador(
                valora.getPuntuacion(),
                valora.getComentario(),
                GetProductoDto.of(valora.getProducto())
        );
    }

    public GetValoraDtoSinTrabajador(int puntuacion, String comentario, Producto producto){
        this(puntuacion, comentario, GetProductoDto.of(producto));
    }
}
