package com.example.Recysell.organizacion.controller;

import com.example.Recysell.categoria.dto.GetCategoriaDto;
import com.example.Recysell.organizacion.dto.EditOrganizacionCmd;
import com.example.Recysell.organizacion.dto.GetOrganizacionDto;
import com.example.Recysell.organizacion.model.Organizacion;
import com.example.Recysell.organizacion.service.OrganizacionService;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/organizacion")
public class OrganizacionController {

    private final OrganizacionService organizacionService;

    @Operation(summary = "Añade una organización.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201",
                description = "La organizacion ha sido registrado correctamente.",
                content = { @Content(mediaType = "application/json",
                        schema = @Schema(implementation = GetOrganizacionDto.class),
                        examples = {@ExampleObject(
                                value = """
                                            {
                                                "nombre": "Caritas",
                                                "direccion": "Calle 123"
                                                
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
    public ResponseEntity<GetOrganizacionDto> save(@Valid @RequestBody EditOrganizacionCmd nuevo){
        Organizacion organizacion = organizacionService.save(nuevo);

        return ResponseEntity.status(HttpStatus.CREATED).body(GetOrganizacionDto.of(organizacion));
    }
}
