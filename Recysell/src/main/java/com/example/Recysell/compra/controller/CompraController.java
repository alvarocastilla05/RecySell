package com.example.Recysell.compra.controller;

import com.example.Recysell.cliente.model.Cliente;
import com.example.Recysell.compra.dto.CreateCompraDto;
import com.example.Recysell.compra.dto.GetCompraConLineaDto;
import com.example.Recysell.compra.dto.GetCompraDto;
import com.example.Recysell.compra.model.Compra;
import com.example.Recysell.compra.service.CompraService;
import com.example.Recysell.error.CompraNotFoundException;
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
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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

    @Operation(summary = "Añade una compra.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "La compra ha sido registrada correctamente.",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = GetCompraDto.class),
                            examples = {@ExampleObject(
                                    value = """
                    {
                      "gastosEnvio": 5.0,
                      "subTotal": 0.0,
                      "fechaVenta": null,
                      "provincia": "Madrid",
                      "codigoPostal": "28001",
                      "direccionEntrega": "Calle Gran Vía, 1",
                      "estadoCompra": "CARRITO",
                      "clienteDto": {
                        "id": "123e4567-e89b-12d3-a456-426614174000",
                        "nombre": "Juan Pérez",
                        "email": "juan.perez@example.com"
                      }
                    }
                    """
                            )}
                    )}
            ),
            @ApiResponse(responseCode = "400",
                    description = "Solicitud incorrecta. Faltan campos obligatorios o el formato es inválido.",
                    content = @Content),
            @ApiResponse(responseCode = "401",
                    description = "No autorizado.",
                    content = @Content)
    })
    @PostMapping
    public ResponseEntity<GetCompraDto> addCompra(@Valid @RequestBody CreateCompraDto dto, @AuthenticationPrincipal Cliente cliente) {
        Compra nuevaCompra = compraService.addCompra(dto, cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(GetCompraDto.of(nuevaCompra));
    }

    @Operation(summary = "Actualiza los datos de entrega de una compra.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "La compra ha sido actualizada correctamente.",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = GetCompraDto.class),
                            examples = {@ExampleObject(
                                    value = """
                                        {
                                            "provincia": "Madrid",
                                            "codigoPostal": "28001",
                                            "direccionEntrega": "Calle Gran Vía, 1"
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
            @ApiResponse(responseCode = "404",
                    description = "Compra no encontrada.",
                    content = @Content),
            @ApiResponse(responseCode = "403",
                    description = "No autorizado para modificar la compra.",
                    content = @Content),
    })
    @PutMapping("/{id}")
    @PreAuthorize("@compraService.esPropietario(#id, authentication.principal.username)")
    public ResponseEntity<GetCompraDto> editarCompra(@PathVariable Long id,
                                                     @Valid @RequestBody CreateCompraDto editCompraDto) {
        Compra compra = compraService.editarCompra(id, editCompraDto);
        return ResponseEntity.ok(GetCompraDto.of(compra));
    }

    @Operation(summary = "Obtiene una compra con sus líneas de venta por id.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha encontrado la compra con sus líneas de venta.",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = GetCompraConLineaDto.class),
                            examples = {@ExampleObject(
                                    value = """
                                {
                                    "gastosEnvio": 5.99,
                                    "subTotal": 49.99,
                                    "fechaVenta": "2023-10-01T10:00:00",
                                    "provincia": "Madrid",
                                    "codigoPostal": "28001",
                                    "direccionEntrega": "Calle Gran Vía, 1",
                                    "estadoCompra": "EN_ENVIO",
                                    "clienteDto": {
                                        "id": 1,
                                        "nombre": "Juan Pérez",
                                        "email": "juan.perez@example.com"
                                    },
                                    "lineasVenta": [
                                        {
                                            "productoId": 101,
                                        },
                                        {
                                            "productoId": 102,
                                        }
                                    ]
                                }
                                """
                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado la compra.",
                    content = @Content),
            @ApiResponse(responseCode = "401",
                    description = "No autorizado.",
                    content = @Content),
    })
    @GetMapping("/{id}")
    @PreAuthorize("@compraService.esPropietario(#id, authentication.principal.username)")
    public GetCompraConLineaDto findById(@PathVariable Long id) {
        Compra compra = compraService.findById(id)
                .orElseThrow(() -> new CompraNotFoundException(id));
        return GetCompraConLineaDto.of(compra);
    }

    @Operation(summary = "Confirma una compra.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",
                    description = "La compra ha sido confirmada correctamente.",
                    content = @Content),
            @ApiResponse(responseCode = "404",
                    description = "Compra no encontrada.",
                    content = @Content),
            @ApiResponse(responseCode = "401",
                    description = "No autorizado.",
                    content = @Content)
    })
    @PutMapping("/{compraId}/confirmar")
    @PreAuthorize("@compraService.esPropietario(#compraId, authentication.principal.username)")
    public ResponseEntity<Void> confirmarCompra(@PathVariable Long compraId) {
        Compra compra = compraService.findById(compraId)
                .orElseThrow(() -> new CompraNotFoundException(compraId));
        compraService.confirmarCompra(compra);
        return ResponseEntity.noContent().build();
    }


}
