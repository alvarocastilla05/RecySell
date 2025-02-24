package com.example.Recysell.producto.controller;

import com.example.Recysell.cliente.dto.ClienteResponse;
import com.example.Recysell.cliente.model.Cliente;
import com.example.Recysell.producto.dto.CreateProductoDto;
import com.example.Recysell.producto.dto.GetProductoDto;
import com.example.Recysell.producto.model.Producto;
import com.example.Recysell.producto.service.ProductoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/producto")
public class ProductoController {

    private final ProductoService productoService;

    @Operation(summary = "Registra un nuevo producto.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "El producto ha sido registrado correctamente.",
                    content = { @Content(mediaType = "application/form-data",
                            schema = @Schema(implementation = ClienteResponse.class),
                            examples = {@ExampleObject(
                                    value = """
                                            {
                                                "nombre": "Ordenador",
                                                "descripcion": "chulo chulo",
                                                "precio": 155.0
                                            }
                                            
                                            
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "400",
                    description = "Solicitud incorrecta. Faltan campos obligatorios o el formato es inválido.",
                    content = @Content),
            @ApiResponse(responseCode = "401",
                    description = "No autorizado.",
                    content = @Content),
    })
    @PostMapping
    @PreAuthorize("hasRole('CLIENTE')")
    public ResponseEntity<GetProductoDto> createProducto(@Valid @RequestPart("producto") CreateProductoDto createProductoDto,
                                                         @AuthenticationPrincipal Cliente authenticatedCliente, @RequestPart("file") MultipartFile file) {
        Producto producto = productoService.save(createProductoDto, authenticatedCliente, file);
        return ResponseEntity.status(HttpStatus.CREATED).body(GetProductoDto.of(producto));


    }
}
