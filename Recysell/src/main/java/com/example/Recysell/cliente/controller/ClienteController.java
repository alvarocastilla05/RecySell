package com.example.Recysell.cliente.controller;

import com.example.Recysell.cliente.dto.ClienteResponse;
import com.example.Recysell.cliente.dto.CreateClienteRequest;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cliente/")
public class ClienteController {

    private final ClienteService clienteService;


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
    @PostMapping("register")
    public ResponseEntity<ClienteResponse> register(@Valid @RequestBody CreateClienteRequest createClienteRequest){
        Cliente cliente = clienteService.createCliente(createClienteRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(ClienteResponse.of(cliente));
    }
}
