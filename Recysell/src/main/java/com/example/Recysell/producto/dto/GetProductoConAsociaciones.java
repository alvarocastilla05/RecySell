package com.example.Recysell.producto.dto;

import com.example.Recysell.categoria.dto.GetCategoriaDto;
import com.example.Recysell.cliente.dto.GetClienteDto;
import com.example.Recysell.producto.model.Producto;

import java.util.Set;

public record GetProductoConAsociaciones(
        String nombre,
        String descripcion,
        double precio,
        String imagen,
        GetClienteDto clienteVendedor,
        Set<GetCategoriaDto> listaCategorias
) {

    public static GetProductoConAsociaciones of(Producto producto, Set<GetCategoriaDto> listaCategorias) {
        return new GetProductoConAsociaciones(
                producto.getNombre(),
                producto.getDescripcion(),
                producto.getPrecio(),
                producto.getImagen(),
                GetClienteDto.of(producto.getClienteVendedor()),
                listaCategorias
        );
    }

}
