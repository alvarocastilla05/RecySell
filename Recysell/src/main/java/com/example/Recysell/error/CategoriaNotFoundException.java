package com.example.Recysell.error;

public class CategoriaNotFoundException extends NotFoundException{

    public CategoriaNotFoundException(Long id){
        super("Categoria con id " + id + " no encontrada");
    }

    public CategoriaNotFoundException(){
        super("No se ha encontrado ninguna categoria");
    }
}
