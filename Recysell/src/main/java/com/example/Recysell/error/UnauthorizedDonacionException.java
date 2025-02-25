package com.example.Recysell.error;

public class UnauthorizedDonacionException extends RuntimeException {
    public UnauthorizedDonacionException(String message) {
        super(message);
    }
}
