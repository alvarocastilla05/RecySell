package com.example.Recysell.compra.controller;

import com.example.Recysell.compra.dto.GetCompraDto;
import com.example.Recysell.compra.model.Compra;
import com.example.Recysell.compra.service.CompraService;
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
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/compra")
public class CompraController {

    private final CompraService compraService;


    @Operation(summary = "Obtiene una lista de compras.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Lista de compras obtenida correctamente.",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = GetCompraDto.class),
                            examples = {@ExampleObject(
                                    value = """
                                {
                                    "gastosEnvio": 5.99,
                                    "subTotal": 49.99,
                                    "fechaVenta": "2023-10-01T10:00:00",
                                    "provincia": "Madrid",
                                    "codigoPostal": "28001",
                                    "direccionEntrega": "Calle Gran Vía, 1",
                                    "clienteDto": {
                                        "id": 1,
                                        "nombre": "Juan Pérez",
                                        "email": "juan.perez@example.com"
                                    }
                                }
                                        """
                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "No se encontraron compras.",
                    content = @Content),
            @ApiResponse(responseCode = "401",
                    description = "No autorizado.",
                    content = @Content)
    })
    @GetMapping
    public Page<GetCompraDto> findAll(@PageableDefault Pageable pageable, boolean isDeleted){
        return compraService.findAll(pageable, isDeleted);
    }

    @Operation(summary = "Obtiene la lista de compras de un Usuario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Lista de compras obtenida correctamente.",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = GetCompraDto.class),
                            examples = {@ExampleObject(
                                    value = """
                                {
                                    "gastosEnvio": 5.99,
                                    "subTotal": 49.99,
                                    "fechaVenta": "2023-10-01T10:00:00",
                                    "provincia": "Madrid",
                                    "codigoPostal": "28001",
                                    "direccionEntrega": "Calle Gran Vía, 1",
                                    "clienteDto": {
                                        "id": 1,
                                        "nombre": "Juan Pérez",
                                        "email": "juan.perez@example.com"
                                    }
                                }
                                        """
                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "No se encontraron compras.",
                    content = @Content),
            @ApiResponse(responseCode = "401",
                    description = "No autorizado.",
                    content = @Content)
    })
    @GetMapping("/cliente/{clienteId}")
    public Page<GetCompraDto> findCompraByCliente(
            @PathVariable UUID clienteId,
            @PageableDefault Pageable pageable,
            boolean isDeleted) {
        return compraService.findCompraByCliente(clienteId, pageable, isDeleted);
    }
}
