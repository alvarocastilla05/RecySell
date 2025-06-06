package com.example.Recysell.donacion.dto;

import com.example.Recysell.donacion.model.Donacion;
import com.example.Recysell.organizacion.dto.GetOrganizacionDto;
import com.example.Recysell.organizacion.model.Organizacion;
import com.example.Recysell.producto.dto.GetProductoDonadoDto;
import com.example.Recysell.producto.dto.GetProductoDto;
import com.example.Recysell.producto.model.Producto;

import java.time.LocalDateTime;

public record GetDonacionDto(
        LocalDateTime fechaDonacion,
        GetOrganizacionDto organizacion,
        GetProductoDonadoDto productoDonado
) {

    public static GetDonacionDto of(Donacion donacion){
        return new GetDonacionDto(
                donacion.getFechaDonacion(),
                GetOrganizacionDto.of(donacion.getOrganizacion()),
                GetProductoDonadoDto.of(donacion.getProductoDonado())
        );
    }

    public GetDonacionDto(LocalDateTime fechaDonacion, Organizacion organizacion, Producto producto){
        this(fechaDonacion, GetOrganizacionDto.of(organizacion), GetProductoDonadoDto.of(producto));
    }
}
