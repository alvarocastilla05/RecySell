package com.example.Recysell.valora.dto;

import com.example.Recysell.producto.dto.GetProductoDto;
import com.example.Recysell.producto.model.Producto;
import com.example.Recysell.trabajador.dto.GetTrabajadorDto;
import com.example.Recysell.trabajador.model.Trabajador;
import com.example.Recysell.valora.model.Valora;

public record GetValoraDto(
        int puntacion,
        String comentario,
        GetTrabajadorDto trabajador,
        GetProductoDto producto
) {

    public static GetValoraDto of(Valora valora){
        return new GetValoraDto(
                valora.getPuntuacion(),
                valora.getComentario(),
                GetTrabajadorDto.of(valora.getTrabajadorValora()),
                GetProductoDto.of(valora.getProducto())
        );
    }

    public GetValoraDto(int puntuacion, String comentario, Trabajador trabajador, Producto producto){
        this(puntuacion, comentario, GetTrabajadorDto.of(trabajador), GetProductoDto.of(producto));
    }
}
