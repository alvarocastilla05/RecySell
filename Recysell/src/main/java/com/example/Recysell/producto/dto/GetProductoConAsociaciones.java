package com.example.Recysell.producto.dto;

import com.example.Recysell.categoria.dto.GetCategoriaDto;
import com.example.Recysell.categoria.model.Categoria;
import com.example.Recysell.cliente.dto.GetClienteDto;
import com.example.Recysell.producto.model.Estado;
import com.example.Recysell.producto.model.Producto;
import com.example.Recysell.valora.dto.GetValoraDtoSinProducto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public record GetProductoConAsociaciones(
        String nombre,
        String descripcion,
        double precio,
        List<String> imagenes,
        Estado estado,
        LocalDateTime fechaRegistro,
        GetClienteDto clienteVendedor,
        GetCategoriaDto categoria,
        GetValoraDtoSinProducto valora

) {

    public static GetProductoConAsociaciones of(Producto producto) {
        return new GetProductoConAsociaciones(
                producto.getNombre(),
                producto.getDescripcion(),
                producto.getPrecio(),
                producto.getImagenes(),
                producto.getEstado(),
                producto.getFechaRegistro(),
                GetClienteDto.of(producto.getClienteVendedor()),
                GetCategoriaDto.of(producto.getCategoria()),
                producto.getValoracion() != null ? GetValoraDtoSinProducto.of(producto.getValoracion()) : null
        );
    }

}
