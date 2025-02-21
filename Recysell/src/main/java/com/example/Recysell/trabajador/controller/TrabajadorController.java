package com.example.Recysell.trabajador.controller;

import com.example.Recysell.trabajador.dto.CreateTrabajadorRequest;
import com.example.Recysell.trabajador.dto.TrabajadorResponse;
import com.example.Recysell.trabajador.model.Trabajador;
import com.example.Recysell.trabajador.service.TrabajadorService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/trabajador/")
public class TrabajadorController {

    private final TrabajadorService trabajadorService;

    @PostMapping("register")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<TrabajadorResponse> register(@RequestBody CreateTrabajadorRequest createTrabajadorRequest){
        Trabajador trabajador = trabajadorService.createTrabajador(createTrabajadorRequest);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(TrabajadorResponse.of(trabajador));
    }


}
