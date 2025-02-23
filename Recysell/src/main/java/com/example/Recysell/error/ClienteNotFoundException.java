package com.example.Recysell.error;

import java.util.UUID;

public class ClienteNotFoundException extends NotFoundException{

    public ClienteNotFoundException(UUID id){
        super("Cliente con id " + id + " no encontrado");
    }

    public ClienteNotFoundException(){
        super("No se ha encontrado ningún cliente");
    }
}
