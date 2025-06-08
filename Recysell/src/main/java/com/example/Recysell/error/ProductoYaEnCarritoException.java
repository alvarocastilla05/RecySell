package com.example.Recysell.error;

public class ProductoYaEnCarritoException extends RuntimeException {
  public ProductoYaEnCarritoException(String mensaje) {
    super(mensaje);
  }
}
