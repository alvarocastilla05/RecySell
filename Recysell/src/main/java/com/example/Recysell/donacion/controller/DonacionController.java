package com.example.Recysell.donacion.controller;

import com.example.Recysell.cliente.dto.ClienteResponse;
import com.example.Recysell.cliente.model.Cliente;
import com.example.Recysell.donacion.dto.EditDonacionCmd;
import com.example.Recysell.donacion.dto.GetDonacionDto;
import com.example.Recysell.donacion.model.Donacion;
import com.example.Recysell.donacion.service.DonacionService;
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
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/donacion")
public class DonacionController {

    private final DonacionService donacionService;


    @Operation(summary = "Registra una nueva donación.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "La donación ha sido registrado correctamente.",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = GetDonacionDto.class),
                            examples = {@ExampleObject(
                                    value = """
                                            {
                                                "fechaDonacion": "2025-03-10T14:30:00",
                                                "productoId": 1,
                                                "organizacionId": 2
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
    public ResponseEntity<GetDonacionDto> save(@Valid @RequestBody EditDonacionCmd nuevo, @AuthenticationPrincipal Cliente cliente){
        Donacion donacion = donacionService.save(nuevo, cliente);

        return  ResponseEntity.status(HttpStatus.CREATED).body(GetDonacionDto.of(donacion));
    }
}
