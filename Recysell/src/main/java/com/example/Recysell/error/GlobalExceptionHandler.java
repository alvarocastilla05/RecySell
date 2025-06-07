package com.example.Recysell.error;

import com.example.Recysell.compra.model.Compra;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Manejo de NotFoundException y sus subclases
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleNotFoundException(NotFoundException ex) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        String errorType = "Recurso no encontrado";

        if (ex instanceof TrabajadorNotFoundException) {
            errorType = "Trabajador no encontrado";
        } else if (ex instanceof ClienteNotFoundException) {
            errorType = "Cliente no encontrado";
        } else if (ex instanceof ProductoNotFoundException) {
            errorType = "Producto no encontrado";
        } else if (ex instanceof CategoriaNotFoundException) {
            errorType = "Categoría no encontrada";
        } else if (ex instanceof OrganizacionNotFoundException) {
            errorType = "Organización no encontrada";
        } else if (ex instanceof ValoraNotFoundException) {
            errorType = "Valoración no encontrada";
        }else if (ex instanceof DonacionNotFoundException) {
            errorType = "Donación no encontrada";
        } else if (ex instanceof LineaVentaNotFoundException) {
            errorType = "Línea de venta no encontrada";
        } else if (ex instanceof CompraNotFoundException) {
            errorType = "Compra no encontrada";
        }

        Map<String, Object> errorBody = Map.of(
                "error", errorType,
                "message", ex.getMessage(),
                "status", status.value()
        );

        return ResponseEntity.status(status).body(errorBody);
    }

    // Manejo de ProductoYaValoradoException
    @ExceptionHandler(ProductoYaValoradoException.class)
    public ResponseEntity<Map<String, Object>> handleProductoYaValoradoException(ProductoYaValoradoException ex) {
        HttpStatus status = HttpStatus.BAD_REQUEST;  // 400 Bad Request for this case
        Map<String, Object> errorBody = Map.of(
                "error", "Producto ya valorado",
                "message", ex.getMessage(),
                "status", status.value()
        );
        return ResponseEntity.status(status).body(errorBody);
    }

    // Manejo de errores de validación (@Valid en los DTOs)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        Map<String, Object> errorResponse = Map.of(
                "error", "Error de validación",
                "message", "Hay errores en los datos enviados",
                "status", HttpStatus.BAD_REQUEST.value(),
                "errors", errors
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(UnauthorizedDonacionException.class)
    public ResponseEntity<Map<String, Object>> handleUnauthorizedDonacion(UnauthorizedDonacionException ex) {
        Map<String, Object> errorBody = Map.of(
                "error", "Acceso no autorizado",
                "message", ex.getMessage(),
                "status", HttpStatus.FORBIDDEN.value()
        );

        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(errorBody);
    }

}
