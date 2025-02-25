package com.example.Recysell.error;

public class DonacionNotFoundException extends NotFoundException{
    public DonacionNotFoundException() {
        super("Donacion no encontrada");
    }
}
