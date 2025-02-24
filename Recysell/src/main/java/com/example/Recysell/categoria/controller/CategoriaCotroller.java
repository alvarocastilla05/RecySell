package com.example.Recysell.categoria.controller;

import com.example.Recysell.categoria.dto.EditarCategoriaCmd;
import com.example.Recysell.categoria.dto.GetCategoriaDto;
import com.example.Recysell.categoria.model.Categoria;
import com.example.Recysell.categoria.service.CategoriaService;
import com.example.Recysell.cliente.dto.ClienteResponse;
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
@RequestMapping("/categoria")
public class CategoriaCotroller {

    private final CategoriaService categoriaService;


    @Operation(summary = "Registra un nueva categoria.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "La categoria ha sido registrado correctamente.",
                    content = { @Content(mediaType = "application/form-data",
                            schema = @Schema(implementation = GetCategoriaDto.class),
                            examples = {@ExampleObject(
                                    value = """
                                            {
                                                "nombre": "tv",
                                                
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
    public ResponseEntity<GetCategoriaDto> save(@Valid @RequestBody EditarCategoriaCmd nuevo){

        Categoria categoria = categoriaService.save(nuevo);

        return ResponseEntity.status(HttpStatus.CREATED).body(GetCategoriaDto.of(categoria));
    }
}
