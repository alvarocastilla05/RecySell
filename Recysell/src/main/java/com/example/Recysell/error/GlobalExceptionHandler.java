package com.example.Recysell.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleNotFoundException(NotFoundException ex) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        String errorType = "Recurso no encontrado";

        if (ex instanceof TrabajadorNotFoundException) {
            errorType = "Trabajador no encontrado";
        }

        Map<String, Object> errorBody = Map.of(
                "error", errorType,
                "message", ex.getMessage(),
                "status", status.value()
        );

        return ResponseEntity.status(status).body(errorBody);
    }
}
