package com.example.Recysell.error;

import java.util.UUID;

public class TrabajadorNotFoundException extends NotFoundException{

    public TrabajadorNotFoundException(UUID id){
        super("Trabajador con id " + id + " no encontrado");
    }

    public TrabajadorNotFoundException(){
        super("No se ha encontrado ningún trabajador");
    }
}
