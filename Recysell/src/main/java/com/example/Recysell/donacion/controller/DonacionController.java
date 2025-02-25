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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/donacion")
public class DonacionController {

    private final DonacionService donacionService;


    @Operation(summary = "Obtiene todas las donaciones.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Lista de donaciones obtenida correctamente.",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = GetDonacionDto.class),
                            examples = {@ExampleObject(
                                    value = """
                                            {
                                               "content": [
                                                   {
                                                       "fechaDonacion": "2025-03-02T15:30:00",
                                                       "organizacion": {
                                                           "nombre": "Green Earth",
                                                           "direccion": "Calle 123, Madrid"
                                                       },
                                                       "productoDonado": {
                                                           "nombre": "MacBook Pro 13\\"",
                                                           "descripcion": "Laptop para trabajo y diseÃ±o",
                                                           "precio": 1299.99,
                                                           "imagen": "imagen3.jpg",
                                                           "clienteVendedor": {
                                                               "username": "martinh",
                                                               "email": "martinh@recycell.com",
                                                               "nombre": "MartÃ­n",
                                                               "apellidos": "HernÃ¡ndez Ruiz"
                                                           }
                                                       }
                                                   },
                                                   {
                                                       "fechaDonacion": "2025-03-01T10:00:00",
                                                       "organizacion": {
                                                           "nombre": "EcoFuture",
                                                           "direccion": "Avenida 45, Barcelona"
                                                       },
                                                       "productoDonado": {
                                                           "nombre": "iPhone 12",
                                                           "descripcion": "TelÃ©fono reacondicionado en excelente estado",
                                                           "precio": 499.99,
                                                           "imagen": "imagen1.jpg",
                                                           "clienteVendedor": {
                                                               "username": "carlosm",
                                                               "email": "carlosm@recycell.com",
                                                               "nombre": "Carlos",
                                                               "apellidos": "MartÃ­nez GÃ³mez"
                                                           }
                                                       }
                                                   },
                                                   {
                                                       "fechaDonacion": "2025-03-07T08:20:00",
                                                       "organizacion": {
                                                           "nombre": "RecyTech",
                                                           "direccion": "Plaza Mayor, Sevilla"
                                                       },
                                                       "productoDonado": {
                                                           "nombre": "Samsung Galaxy S21",
                                                           "descripcion": "Smartphone de alto rendimiento",
                                                           "precio": 599.99,
                                                           "imagen": "imagen2.jpg",
                                                           "clienteVendedor": {
                                                               "username": "carlosm",
                                                               "email": "carlosm@recycell.com",
                                                               "nombre": "Carlos",
                                                               "apellidos": "MartÃ­nez GÃ³mez"
                                                           }
                                                       }
                                                   },
                                                   {
                                                       "fechaDonacion": "2025-03-05T12:45:00",
                                                       "organizacion": {
                                                           "nombre": "EcoRecycle",
                                                           "direccion": "Calle 67, Valencia"
                                                       },
                                                       "productoDonado": {
                                                           "nombre": "Teclado MecÃ¡nico",
                                                           "descripcion": "Teclado gaming RGB",
                                                           "precio": 99.99,
                                                           "imagen": "imagen5.jpg",
                                                           "clienteVendedor": {
                                                               "username": "fernandor",
                                                               "email": "fernandor@recycell.com",
                                                               "nombre": "Fernando",
                                                               "apellidos": "RamÃ­rez Ortega"
                                                           }
                                                       }
                                                   },
                                                   {
                                                       "fechaDonacion": "2025-03-10T18:15:00",
                                                       "organizacion": {
                                                           "nombre": "GreenTech",
                                                           "direccion": "Avenida 89, Bilbao"
                                                       },
                                                       "productoDonado": {
                                                           "nombre": "Monitor LG 4K",
                                                           "descripcion": "Monitor UHD para productividad",
                                                           "precio": 299.99,
                                                           "imagen": "imagen4.jpg",
                                                           "clienteVendedor": {
                                                               "username": "martinh",
                                                               "email": "martinh@recycell.com",
                                                               "nombre": "MartÃ­n",
                                                               "apellidos": "HernÃ¡ndez Ruiz"
                                                           }
                                                       }
                                                   }
                                               ],
                                               "pageable": {
                                                   "pageNumber": 0,
                                                   "pageSize": 10,
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
                                               "totalElements": 5,
                                               "first": true,
                                               "size": 10,
                                               "number": 0,
                                               "sort": {
                                                   "empty": true,
                                                   "sorted": false,
                                                   "unsorted": true
                                               },
                                               "numberOfElements": 5,
                                               "empty": false
                                    }
                                    """
                            )}
                    )}),
            @ApiResponse(responseCode = "401",
                    description = "No autorizado.",
                    content = @Content),
            @ApiResponse(responseCode = "404",
                    description = "No se han encontrado donaciones.",
                    content = @Content)
    })
    @GetMapping
    public Page<GetDonacionDto> findAll(@PageableDefault Pageable pageable){
        return donacionService.findAll(pageable);
    }

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
