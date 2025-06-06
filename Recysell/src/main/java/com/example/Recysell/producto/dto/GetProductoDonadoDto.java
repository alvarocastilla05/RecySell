package com.example.Recysell.producto.dto;

import com.example.Recysell.categoria.dto.GetCategoriaDto;
import com.example.Recysell.categoria.model.Categoria;
import com.example.Recysell.cliente.dto.GetClienteDto;
import com.example.Recysell.cliente.model.Cliente;
import com.example.Recysell.producto.model.Estado;
import com.example.Recysell.producto.model.Producto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public record GetProductoDonadoDto(
        String nombre,
        String descripcion,
        double precio,
        List<String> imagenes,
        Estado estado,
        LocalDateTime fechaRegistro,
        GetCategoriaDto categoria,
        GetClienteDto clienteDonador
) {

    public static GetProductoDonadoDto of(Producto producto) {
        return new GetProductoDonadoDto(
                producto.getNombre(),
                producto.getDescripcion(),
                producto.getPrecio(),
                producto.getImagenes(),
                producto.getEstado(),
                producto.getFechaRegistro(),
                GetCategoriaDto.of(producto.getCategoria()),
                GetClienteDto.of(producto.getClienteDonante())

        );
    }

    public GetProductoDonadoDto(String nombre, String descripcion, double precio, List<String> imagenes, Estado estado, LocalDateTime fechaRegistro, Categoria categoria,  Cliente clienteDonante) {
        this(nombre, descripcion, precio, imagenes, estado, fechaRegistro, GetCategoriaDto.of(categoria) , GetClienteDto.of(clienteDonante));
    }


}
