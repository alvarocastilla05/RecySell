package com.example.Recysell.error;

public class TrabajadorNotFoundException extends NotFoundException{
    public TrabajadorNotFoundException(String username) {
        super("Trabajador con username " + username + " no encontrado");
    }

    public TrabajadorNotFoundException(){
        super("No se ha encontrado ningún trabajador");
    }
}
