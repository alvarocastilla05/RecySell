package com.example.Recysell.error;

public class ClienteNotFoundException extends NotFoundException{

    public ClienteNotFoundException(String id){
        super("Cliente con id " + id + " no encontrado");
    }

    public ClienteNotFoundException(){
        super("No se ha encontrado ningún cliente");
    }
}
