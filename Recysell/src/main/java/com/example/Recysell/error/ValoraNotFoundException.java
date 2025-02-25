package com.example.Recysell.error;

import com.example.Recysell.valora.model.Valora;

public class ValoraNotFoundException extends NotFoundException{

    public ValoraNotFoundException(){
        super("Valoración no encontrada");
    }
}
