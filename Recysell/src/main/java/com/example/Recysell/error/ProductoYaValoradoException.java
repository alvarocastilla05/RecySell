package com.example.Recysell.error;

public class ProductoYaValoradoException extends RuntimeException{

    public ProductoYaValoradoException(){
        super("El producto ya ha sido valorado");
    }
}
