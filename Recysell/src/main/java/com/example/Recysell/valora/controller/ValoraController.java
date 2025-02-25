package com.example.Recysell.valora.controller;

import com.example.Recysell.categoria.dto.GetCategoriaDto;
import com.example.Recysell.trabajador.model.Trabajador;
import com.example.Recysell.valora.dto.CreateValoraRequest;
import com.example.Recysell.valora.dto.EditValoraCmd;
import com.example.Recysell.valora.dto.GetValoraDto;
import com.example.Recysell.valora.dto.GetValoraDtoSinTrabajador;
import com.example.Recysell.valora.model.Valora;
import com.example.Recysell.valora.service.ValoraService;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.awt.font.TransformAttribute;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/valora")
public class ValoraController {

    private final ValoraService valoraService;


    @Operation(summary = "Obtiene todas las valoraciones de un trabajador.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Valoraciones obtenidas correctamente.",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = GetValoraDtoSinTrabajador.class),
                            examples = {@ExampleObject(
                                    value = """
                                            {
                                                "content": [
                                                    {
                                                        "puntuacion": 5,
                                                        "comentario": "RÃ¡pido y eficiente, perfecto para mi trabajo.",
                                                        "producto": {
                                                            "nombre": "MacBook Pro 13\\"",
                                                            "descripcion": "Laptop para trabajo y diseÃ±o",
                                                            "precio": 1299.99,
                                                            "imagen": "imagen3.jpg",
                                                            "clienteVendedor": {
                                                                "username": "martinh",
                                                                "email": "martinh@recycell.com",
                                                                "nombre": "MartÃ­n",
                                                                "apellidos": "HernÃ¡ndez Ruiz"
                                                            }
                                                        }
                                                    },
                                                    {
                                                        "puntuacion": 4,
                                                        "comentario": "Muy Ãºtil, aunque la baterÃ­a podrÃ­a durar mÃ¡s.",
                                                        "producto": {
                                                            "nombre": "Apple Watch Series 6",
                                                            "descripcion": "Reloj inteligente con monitor de oxÃ­geno en sangre",
                                                            "precio": 399.99,
                                                            "imagen": "imagen8.jpg",
                                                            "clienteVendedor": {
                                                                "username": "fernandor",
                                                                "email": "fernandor@recycell.com",
                                                                "nombre": "Fernando",
                                                                "apellidos": "RamÃ­rez Ortega"
                                                            }
                                                        }
                                                    }
                                                ],
                                                "pageable": {
                                                    "pageNumber": 0,
                                                    "pageSize": 20,
                                                    "sort": {
                                                        "empty": true,
                                                        "sorted": false,
                                                        "unsorted": true
                                                    },
                                                    "offset": 0,
                                                    "paged": true,
                                                    "unpaged": false
                                                },
                                                "last": true,
                                                "totalElements": 2,
                                                "totalPages": 1,
                                                "first": true,
                                                "size": 20,
                                                "number": 0,
                                                "sort": {
                                                    "empty": true,
                                                    "sorted": false,
                                                    "unsorted": true
                                                },
                                                "numberOfElements": 2,
                                                "empty": false
                                            }
                                            
                                            
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "No se encontraron valoraciones para el trabajador.",
                    content = @Content),
            @ApiResponse(responseCode = "401",
                    description = "No autorizado.",
                    content = @Content),
    })
    @GetMapping("/trabajador/{id}")
    public Page<GetValoraDtoSinTrabajador> getValoracionesByTrabajador(@PathVariable("id")UUID id,
                                                                       Pageable pageable) {
        return valoraService.findAllByTrabajadorValora(id, pageable);
    }

    @Operation(summary = "Registra un nueva valoración.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "La valoración ha sido registrado correctamente.",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = GetValoraDto.class),
                            examples = {@ExampleObject(
                                    value = """
                                            {
                                               "puntuacion": 4,
                                               "comentario": "Buen producto, pero podría mejorar la calidad de la cámara.",
                                               "productoId": 3
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
    public ResponseEntity<GetValoraDto> save(@Valid @RequestBody CreateValoraRequest nuevo, @AuthenticationPrincipal Trabajador trabajador){
        Valora valora = valoraService.save(nuevo, trabajador);

        return ResponseEntity.status(HttpStatus.CREATED).body(GetValoraDto.of(valora));

    }

    @Operation(summary = "Edita una valoración.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "La valoración ha sido editada correctamente.",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = GetValoraDto.class),
                            examples = {@ExampleObject(
                                    value = """
                                            {
                                               "puntuacion": 4,
                                               "comentario": "Buen producto, pero podría mejorar la calidad de la cámara.",
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
                    description = "La valoración no existe.",
                    content = @Content),
    })
    @PutMapping("/{id}")
    public ResponseEntity<GetValoraDto> edit(@PathVariable("id") Long id, @Valid @RequestBody EditValoraCmd editar, @AuthenticationPrincipal Trabajador trabajador){
        Valora valora = valoraService.edit(trabajador, editar, id);

        return ResponseEntity.ok(GetValoraDto.of(valora));
    }

    @Operation(summary = "Elimina una valoración.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",
                    description = "La valoración ha sido eliminada correctamente.",
                    content = @Content),
            @ApiResponse(responseCode = "401",
                    description = "No autorizado.",
                    content = @Content),
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id, @AuthenticationPrincipal Trabajador trabajador){
        valoraService.deleteById(trabajador, id);
        return ResponseEntity.noContent().build();
    }
}
