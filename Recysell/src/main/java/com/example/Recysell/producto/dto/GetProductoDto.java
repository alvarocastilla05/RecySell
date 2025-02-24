package com.example.Recysell.producto.dto;

import com.example.Recysell.cliente.dto.GetClienteDto;
import com.example.Recysell.cliente.model.Cliente;
import com.example.Recysell.producto.model.Producto;

public record GetProductoDto(
        String nombre,
        String descripcion,
        double precio,
        String imagen,
        GetClienteDto clienteVendedor
) {

    public static GetProductoDto of(Producto producto){
        return new GetProductoDto(
                producto.getNombre(),
                producto.getDescripcion(),
                producto.getPrecio(),
                producto.getImagen(),
                GetClienteDto.of(producto.getClienteVendedor())
        );
    }

    public GetProductoDto(String nombre, String descripcion, double precio, String imagen, Cliente clienteVendedor) {
        this(nombre, descripcion, precio, imagen, GetClienteDto.of(clienteVendedor));
    }


}
