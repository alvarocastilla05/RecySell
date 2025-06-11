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

public record GetProductoDto(
        Long id,
        String nombre,
        String descripcion,
        double precio,
        List<String> imagenes,
        Estado estado,
        LocalDateTime fechaRegistro,
        GetCategoriaDto categoria,
        GetClienteDto clienteVendedor
) {

    public static GetProductoDto of(Producto producto) {
        return new GetProductoDto(
                producto.getId(),
                producto.getNombre(),
                producto.getDescripcion(),
                producto.getPrecio(),
                producto.getImagenes(),
                producto.getEstado(),
                producto.getFechaRegistro(),
                GetCategoriaDto.of(producto.getCategoria()),
                GetClienteDto.of(producto.getClienteVendedor())

        );
    }

    public GetProductoDto(Long id, String nombre, String descripcion, double precio, List<String> imagenes, Estado estado, LocalDateTime fechaRegistro, Categoria categoria,  Cliente clienteVendedor) {
        this(id, nombre, descripcion, precio, imagenes, estado, fechaRegistro, GetCategoriaDto.of(categoria) , GetClienteDto.of(clienteVendedor));
    }


}
