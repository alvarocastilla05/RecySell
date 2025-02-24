package com.example.Recysell.trabajador.controller;

import com.example.Recysell.trabajador.dto.CreateTrabajadorRequest;
import com.example.Recysell.trabajador.dto.EditTrabajadorCmd;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/trabajador")
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
                                                     "content": [
                                                         {
                                                             "username": "jrodriguez",
                                                             "email": "jrodriguez@recycell.com",
                                                             "nombre": "Juan",
                                                             "apellidos": "RodrÃ­guez PÃ©rez",
                                                             "puesto": "TÃ©cnico en reacondicionamiento"
                                                         },
                                                         {
                                                             "username": "mlopez",
                                                             "email": "mlopez@recycell.com",
                                                             "nombre": "MarÃ­a",
                                                             "apellidos": "LÃ³pez FernÃ¡ndez",
                                                             "puesto": "TÃ©cnico en pruebas"
                                                         },
                                                         {
                                                             "username": "agomez",
                                                             "email": "agomez@recycell.com",
                                                             "nombre": "Alejandro",
                                                             "apellidos": "GÃ³mez SÃ¡nchez",
                                                             "puesto": "TÃ©cnico en ventas"
                                                         }
                                                     ],
                                                     "pageable": {
                                                         "pageNumber": 0,
                                                         "pageSize": 5,
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
                                                     "totalPages": 1,
                                                     "totalElements": 3,
                                                     "size": 5,
                                                     "number": 0,
                                                     "sort": {
                                                         "empty": true,
                                                         "sorted": false,
                                                         "unsorted": true
                                                     },
                                                     "first": true,
                                                     "numberOfElements": 3,
                                                     "empty": false
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
    public Page<GetTrabajadorDto> findAll(@RequestParam(defaultValue = "0") int page,
                                          @RequestParam(defaultValue = "10") int size,
                                          @RequestParam(value = "isDeleted", required = false, defaultValue = "false") boolean isDeleted){
        return trabajadorService.findAll(PageRequest.of(page, size), isDeleted);
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
    @GetMapping("/{id}")
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
    @PostMapping("/register")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<TrabajadorResponse> register(@Valid @RequestBody CreateTrabajadorRequest createTrabajadorRequest){
        Trabajador trabajador = trabajadorService.createTrabajador(createTrabajadorRequest);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(TrabajadorResponse.of(trabajador));
    }


    @Operation(summary = "Edita un trabajador.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "El trabajador ha sido editado correctamente.",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = GetTrabajadorDto.class),
                            examples = {@ExampleObject(
                                    value = """
                                            {
                                                    "username": "jrodriguez",
                                                    "email": "jrodriguez@recycell.com",
                                                    "password": "123456",
                                                    "nombre": "javier",
                                                    "apellidos": "rodriguez"
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
            @ApiResponse(responseCode = "403",
                    description = "No tienes permiso para editar este trabajador.",
                    content = @Content)
    })
    @PutMapping("/{id}")
    @PostAuthorize("returnObject.username == authentication.principal.username")
    public GetTrabajadorDto edit(@Valid @RequestBody EditTrabajadorCmd editTrabajadorCmd, @PathVariable("id") UUID id) {
        Trabajador trabajador = trabajadorService.edit(editTrabajadorCmd, id);

        return GetTrabajadorDto.of(trabajador);
    }

    @Operation(summary = "Elimina un trabajador.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",
                    description = "El trabajador ha sido eliminado correctamente.",
                    content = @Content),
            @ApiResponse(responseCode = "401",
                    description = "No autorizado.",
                    content = @Content),
    })
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteById(@PathVariable("id") UUID id){
        trabajadorService.deleteById(id);

        return ResponseEntity.noContent().build();
    }


}
