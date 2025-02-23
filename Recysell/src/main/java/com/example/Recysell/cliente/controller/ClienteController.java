package com.example.Recysell.cliente.controller;

import com.example.Recysell.cliente.dto.ClienteResponse;
import com.example.Recysell.cliente.dto.CreateClienteRequest;
import com.example.Recysell.cliente.dto.GetClienteDto;
import com.example.Recysell.cliente.model.Cliente;
import com.example.Recysell.cliente.service.ClienteService;
import com.example.Recysell.trabajador.dto.TrabajadorResponse;
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
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;


import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cliente")
public class ClienteController {

    private final ClienteService clienteService;

    @Operation(summary = "Obtiene una lista de todos los clientes.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Lista de clientes obtenida correctamente.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = GetClienteDto.class),
                            examples = {@ExampleObject(
                                    value = """
                                            {
                                                     "content": [
                                                         {
                                                             "username": "carlosm",
                                                             "email": "carlosm@recycell.com",
                                                             "nombre": "Carlos",
                                                             "apellidos": "MartÃ­nez GÃ³mez"
                                                         },
                                                         {
                                                             "username": "laurap",
                                                             "email": "laurap@recycell.com",
                                                             "nombre": "Laura",
                                                             "apellidos": "PÃ©rez DomÃ­nguez"
                                                         },
                                                         {
                                                             "username": "martinh",
                                                             "email": "martinh@recycell.com",
                                                             "nombre": "MartÃ­n",
                                                             "apellidos": "HernÃ¡ndez Ruiz"
                                                         },
                                                         {
                                                             "username": "andreag",
                                                             "email": "andreag@recycell.com",
                                                             "nombre": "Andrea",
                                                             "apellidos": "GarcÃ­a LÃ³pez"
                                                         },
                                                         {
                                                             "username": "fernandor",
                                                             "email": "fernandor@recycell.com",
                                                             "nombre": "Fernando",
                                                             "apellidos": "RamÃ­rez Ortega"
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
                                                     "totalElements": 5,
                                                     "totalPages": 1,
                                                     "first": true,
                                                     "numberOfElements": 5,
                                                     "size": 5,
                                                     "number": 0,
                                                     "sort": {
                                                         "empty": true,
                                                         "sorted": false,
                                                         "unsorted": true
                                                     },
                                                     "empty": false
                                                    
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado ningún cliente.",
                    content = @Content)
    })
    @GetMapping
    public Page<GetClienteDto> findAll(@RequestParam(defaultValue = "0") int page,
                                       @RequestParam(defaultValue = "10") int size) {
        return clienteService.findAll(PageRequest.of(page, size));
    }



    @Operation(summary = "Registra un nuevo cliente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "El cliente ha sido registrado correctamente.",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ClienteResponse.class),
                            examples = {@ExampleObject(
                                    value = """
                                            {
                                                 "username": "juanperez",
                                                 "email": "castilla.caalv24@triana.salesianos.edu",
                                                 "password": "12345",
                                                 "nombre": "Juan",
                                                 "apellido": "Pérez"
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
    public ResponseEntity<ClienteResponse> register(@Valid @RequestBody CreateClienteRequest createClienteRequest){
        Cliente cliente = clienteService.createCliente(createClienteRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(ClienteResponse.of(cliente));
    }
}
