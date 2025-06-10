package com.example.Recysell.error;

public class CompraNotFoundException extends NotFoundException {

    public CompraNotFoundException(Long id) {
        super("Compra con id " + id + " no encontrado");
    }

    public CompraNotFoundException() {
        super("No se ha encontrado ninguna compra");
    }
}
