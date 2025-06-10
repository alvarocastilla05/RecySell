package com.example.Recysell.error;

import com.example.Recysell.producto.model.Producto;

public class ProductoNotFoundException extends NotFoundException{

    public ProductoNotFoundException(Long id){
        super("Producto con id " + id + " no encontrado");
    }

    public ProductoNotFoundException(){
        super("No se ha encontrado ningún producto");
    }

    public ProductoNotFoundException(String message){
        super(message);
    }
}
