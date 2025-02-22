package com.example.Recysell.trabajador.controller;

import com.example.Recysell.trabajador.dto.CreateTrabajadorRequest;
import com.example.Recysell.trabajador.dto.GetTrabajadorDto;
import com.example.Recysell.trabajador.dto.TrabajadorResponse;
import com.example.Recysell.trabajador.model.Trabajador;
import com.example.Recysell.trabajador.service.TrabajadorService;
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
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/trabajador/")
public class TrabajadorController {

    private final TrabajadorService trabajadorService;


    @Operation(summary = "Obtiene una lista de todos los trabajadores.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Lista de trabajadores obtenida correctamente.",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = GetTrabajadorDto.class),
                            examples = {@ExampleObject(
                                    value = """
                                            {
                                                 "username": "juanperez",
                                                 "email": "castilla.caal24@triana.salesianos.edu",
                                                    "nombre": "Juan",
                                                    "apellidos": "Pérez",
                                                    "puesto": "Operario"
                                                }
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado ningún trabajador.",
                    content = @Content),
            @ApiResponse(responseCode = "401",
                    description = "No autorizado.",
                    content = @Content),
    })
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<GetTrabajadorDto> findAll(){
        return trabajadorService.findAll();
    }


    @Operation(summary = "Obtiene un trabajador por su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Trabajador obtenido correctamente.",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = GetTrabajadorDto.class),
                            examples = {@ExampleObject(
                                    value = """
                                            {
                                                 "username": "jrodriguez",
                                                 "email": "jrodriguez@recysell.com",
                                                    "nombre": "null",
                                                    "apellidos": "null",
                                                    "puesto": "Tecnico en Reacondicionamiento"
                                                }
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado ningún trabajador con ese ID.",
                    content = @Content),
            @ApiResponse(responseCode = "401",
                    description = "No autorizado.",
                    content = @Content)
    })
    @GetMapping("{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public GetTrabajadorDto findById(@PathVariable("id") UUID id){
        Trabajador trabajador = trabajadorService.findById(id);

        return GetTrabajadorDto.of(trabajador);
    }


    @Operation(summary = "Registra un nuevo trabajador.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "El trabajador ha sido registrado correctamente.",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TrabajadorResponse.class),
                            examples = {@ExampleObject(
                                    value = """
                                            {
                                                 "username": "juanperez",
                                                 "email": "castilla.caalv24@triana.salesianos.edu",
                                                 "password": "12345",
                                                 "nombre": "Juan",
                                                 "apellido": "Pérez",
                                                 "puesto": "Operario"
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
    @PostMapping("register")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<TrabajadorResponse> register(@Valid @RequestBody CreateTrabajadorRequest createTrabajadorRequest){
        Trabajador trabajador = trabajadorService.createTrabajador(createTrabajadorRequest);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(TrabajadorResponse.of(trabajador));
    }


}
