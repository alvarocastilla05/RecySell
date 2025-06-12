package com.example.Recysell.valora.dto;

import com.example.Recysell.producto.dto.GetProductoDto;
import com.example.Recysell.producto.model.Producto;
import com.example.Recysell.trabajador.dto.GetTrabajadorDto;
import com.example.Recysell.trabajador.model.Trabajador;
import com.example.Recysell.valora.model.Valora;

public record GetValoraDtoSinProducto(
        int puntacion,
        String comentario,
        GetTrabajadorDto trabajador
) {

    public static GetValoraDtoSinProducto of(Valora valora){
        return new GetValoraDtoSinProducto(
                valora.getPuntuacion(),
                valora.getComentario(),
                GetTrabajadorDto.of(valora.getTrabajadorValora())
        );
    }

    public GetValoraDtoSinProducto(int puntuacion, String comentario, Trabajador trabajador){
        this(puntuacion, comentario, GetTrabajadorDto.of(trabajador));
    }
}
