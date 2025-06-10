package com.example.Recysell.error;

public class CompraCanceladaException extends RuntimeException {
    public CompraCanceladaException() {
        super("La compra no puede ser cancelada.");
    }
}
