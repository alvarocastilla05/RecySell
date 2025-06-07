package com.example.Recysell.error;

public class LineaVentaNotFoundException extends NotFoundException {

    public LineaVentaNotFoundException(Long id) {
        super("Linea de venta con id " + id + " no encontrada");
    }
    public LineaVentaNotFoundException() {
        super("No se ha encontrado ninguna linea de venta");
    }
}
