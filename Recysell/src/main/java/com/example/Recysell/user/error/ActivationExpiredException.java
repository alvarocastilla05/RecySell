package com.example.Recysell.user.error;

public class ActivationExpiredException extends RuntimeException{

    public ActivationExpiredException(String s) {
        super(s);
    }
}
