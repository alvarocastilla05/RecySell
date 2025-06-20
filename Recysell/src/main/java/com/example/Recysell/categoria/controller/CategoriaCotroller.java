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
import org.springframework.web.multipart.MultipartFile;

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
    public ResponseEntity<GetCategoriaDto> save(@Valid @RequestPart("categoria") EditarCategoriaCmd nuevo,
                                                @RequestPart("file")MultipartFile file){

        Categoria categoria = categoriaService.save(nuevo, file);

        return ResponseEntity.status(HttpStatus.CREATED).body(GetCategoriaDto.of(categoria));
    }

    @Operation(summary = "Edita una categoria.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "La categoria ha sido editada correctamente.",
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
            @ApiResponse(responseCode = "400",
                    description = "Solicitud incorrecta. Faltan campos obligatorios o el formato es inválido.",
                    content = @Content),
            @ApiResponse(responseCode = "401",
                    description = "No autorizado.",
                    content = @Content),
    })
    @PutMapping("/{id}")
    public GetCategoriaDto edit(@PathVariable Long id, @Valid @RequestPart("categoria") EditarCategoriaCmd edit,
                                @RequestPart("file") MultipartFile file){
        return GetCategoriaDto.of(categoriaService.edit(id, edit, file));
    }

    @Operation(summary = "Elimina una categoria.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",
                    description = "La categoria ha sido eliminada correctamente.",
                    content = @Content),
            @ApiResponse(responseCode = "401",
                    description = "No autorizado.",
                    content = @Content),
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        categoriaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
