package com.example.Recysell.valora.controller;

import com.example.Recysell.categoria.dto.GetCategoriaDto;
import com.example.Recysell.trabajador.model.Trabajador;
import com.example.Recysell.valora.dto.CreateValoraRequest;
import com.example.Recysell.valora.dto.GetValoraDto;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.font.TransformAttribute;

@RestController
@RequiredArgsConstructor
@RequestMapping("/valora")
public class ValoraController {

    private final ValoraService valoraService;

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
}
