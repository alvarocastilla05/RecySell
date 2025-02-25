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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/categoria")
public class CategoriaCotroller {

    private final CategoriaService categoriaService;


    @Operation(summary = "Obtiene todas las categorias.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se han encontrado todas las categorias.",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = GetCategoriaDto.class),
                            examples = {@ExampleObject(
                                    value = """
                                            {
                                                "nombre": "tv",
                                                
                                            }
                                            
                                            
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "No se han encontrado categorias.",
                    content = @Content),
    })
    @GetMapping
    public Page<GetCategoriaDto> findAll(@PageableDefault Pageable pageable, boolean isDeleted){
        return categoriaService.findAll(pageable, isDeleted);
    }

    @Operation(summary = "Obtiene una categoria por id.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha encontrado la categoria.",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = GetCategoriaDto.class),
                            examples = {@ExampleObject(
                                    value = """
                                            {
                                                "nombre": "smartphones",
                                                
                                            }
                                            
                                            
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado la categoria.",
                    content = @Content),
            @ApiResponse(responseCode = "401"
                    , description = "No autorizado.",
                    content = @Content),
    })
    @GetMapping("/{id}")
    public GetCategoriaDto findById(@PathVariable Long id){
        Categoria categoria = categoriaService.findById(id);
        return GetCategoriaDto.of(categoria);
    }


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
