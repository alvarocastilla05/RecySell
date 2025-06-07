package com.example.Recysell.lineaVenta.controller;

import com.example.Recysell.lineaVenta.dto.GetLineaVentaDto;
import com.example.Recysell.lineaVenta.service.LineaVentaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}