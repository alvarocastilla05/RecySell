package com.example.Recysell.lineaVenta.controller;

import com.example.Recysell.lineaVenta.dto.CreateLineaVentaDto;
import com.example.Recysell.lineaVenta.dto.GetLineaVentaDto;
import com.example.Recysell.lineaVenta.model.LineaVenta;
import com.example.Recysell.lineaVenta.service.LineaVentaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/linea-venta")
public class LineaVentaController {

    private final LineaVentaService lineaVentaService;

    @Operation(summary = "Obtiene todas las líneas de venta.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se han encontrado todas las líneas de venta.",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = GetLineaVentaDto.class),
                            examples = {@ExampleObject(
                                    value = """
                                        {
                                            "productoDto": {
                                                "nombre": "Producto 1",
                                                "precio": 100.0
                                            },
                                            "compraDto": {
                                                "gastosEnvio": 10.0,
                                                "subTotal": 90.0,
                                                "fechaVenta": "2023-10-01T10:00:00",
                                                "provincia": "Madrid",
                                                "codigoPostal": "28001",
                                                "direccionEntrega": "Calle Gran Vía, 1",
                                                "clienteDto": {
                                                    "nombre": "Juan Pérez",
                                                    "email": "juan.perez@example.com"
                                                }
                                            }
                                        }
                                        """
                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "No se han encontrado líneas de venta.",
                    content = @Content),
            @ApiResponse(responseCode = "401",
                    description = "No autorizado.",
                    content = @Content)
    })
    @GetMapping
    public Page<GetLineaVentaDto> findAll(@PageableDefault Pageable pageable, @RequestParam(defaultValue = "false") boolean isDeleted) {
        return lineaVentaService.findAll(pageable, isDeleted);
    }

    @Operation(summary = "Registra una nueva línea de venta.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "La línea de venta ha sido registrada correctamente.",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = GetLineaVentaDto.class),
                            examples = {@ExampleObject(
                                    value = """
                                            {
                                                "productoDto": {
                                                    "nombre": "Producto 1",
                                                    "precio": 100.0
                                                },
                                                "compraDto": {
                                                    "gastosEnvio": 10.0,
                                                    "subTotal": 90.0,
                                                    "fechaVenta": "2023-10-01T10:00:00",
                                                    "provincia": "Madrid",
                                                    "codigoPostal": "28001",
                                                    "direccionEntrega": "Calle Gran Vía, 1",
                                                    "clienteDto": {
                                                        "nombre": "Juan Pérez",
                                                        "email": "juan.perez@example.com"
                                                    }
                                                }
                                            }
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "400",
                    description = "Solicitud incorrecta. Faltan campos obligatorios o el formato es inválido.",
                    content = @Content),
            @ApiResponse(responseCode = "404",
                    description = "Compra o producto no encontrados.",
                    content = @Content),
            @ApiResponse(responseCode = "401",
                    description = "No autorizado.",
                    content = @Content),
    })
    @PostMapping
    public ResponseEntity<GetLineaVentaDto> createLineaVenta(@Valid @RequestBody CreateLineaVentaDto createLineaVentaDto) {
        LineaVenta lineaVenta = lineaVentaService.save(createLineaVentaDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(GetLineaVentaDto.of(lineaVenta));
    }

    @Operation(summary = "Elimina una línea de venta.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",
                    description = "La línea de venta ha sido eliminada correctamente.",
                    content = @Content),
            @ApiResponse(responseCode = "401",
                    description = "No autorizado.",
                    content = @Content),
            @ApiResponse(responseCode = "403",
                    description = "No autorizado para eliminar la línea de venta.",
                    content = @Content),
    })
    @DeleteMapping("/{id}")
    @PreAuthorize("@lineaVentaService.esPropietario(#id, authentication.principal.username)")
    public ResponseEntity<?> deleteLineaVenta(@PathVariable Long id) {
        lineaVentaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
