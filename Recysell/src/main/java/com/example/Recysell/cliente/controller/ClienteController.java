package com.example.Recysell.cliente.controller;

import com.example.Recysell.cliente.dto.ClienteResponse;
import com.example.Recysell.cliente.dto.CreateClienteRequest;
import com.example.Recysell.cliente.dto.EditClienteCmd;
import com.example.Recysell.cliente.dto.GetClienteDto;
import com.example.Recysell.cliente.model.Cliente;
import com.example.Recysell.cliente.service.ClienteService;
import com.example.Recysell.producto.dto.GetProductoDto;
import com.example.Recysell.producto.model.Producto;
import com.example.Recysell.trabajador.dto.TrabajadorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;


import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

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
    public Page<GetClienteDto> findAll(@PageableDefault Pageable pageable,
                                       @RequestParam(value = "isDeleted", required = false, defaultValue = "false") boolean isDeleted){
        return clienteService.findAll(pageable, isDeleted);
    }

    @Operation(summary = "Obtiene un cliente por su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Cliente obtenido correctamente.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = GetClienteDto.class),
                            examples = {@ExampleObject(
                                    value = """
                                            {
                                                 "username": "carlosm",
                                                 "email": "carlosm@recycell.com",
                                                 "nombre": "Carlos",
                                                 "apellidos": "MartÃ­nez GÃ³mez"
                                            }
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "Cliente no encontrado.",
                    content = @Content)
    })
    @GetMapping("/{id}")
    public GetClienteDto findById(@PathVariable("id") UUID id){
        Cliente cliente = clienteService.findById(id);

        return GetClienteDto.of(cliente);
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
    public ResponseEntity<ClienteResponse> register(@RequestBody @Validated CreateClienteRequest createClienteRequest){
        Cliente cliente = clienteService.createCliente(createClienteRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(ClienteResponse.of(cliente));
    }

    @Operation(summary = "Edita un cliente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Cliente editado correctamente.",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = GetClienteDto.class),
                            examples = {@ExampleObject(
                                    value = """
                                            {
                                                 "username": "carlosm",
                                                 "email": "carlosm@recycell.com",
                                                 "nombre": "sisi",
                                                 "password": "123456",
                                                 "apellidos": "MartÃ­nez GÃ³mez"
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
            @ApiResponse(responseCode = "404",
                    description = "Cliente no encontrado.",
                    content = @Content)
    })
    @PutMapping("/{id}")
    @PostAuthorize("returnObject.username == authentication.principal.username")
    public GetClienteDto edit(@Valid @RequestBody EditClienteCmd editClienteCmd, @PathVariable UUID id){
        Cliente cliente = clienteService.edit(editClienteCmd, id);

        return GetClienteDto.of(cliente);
    }

    @Operation(summary = "Elimina un cliente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",
                    description = "Cliente eliminado correctamente.",
                    content = @Content),
            @ApiResponse(responseCode = "401",
                    description = "No autorizado.",
                    content = @Content)
    })
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or #id == authentication.principal.id")
    public ResponseEntity<?> deleteById(@PathVariable("id") UUID id) {
        clienteService.deleteById(id);
        return ResponseEntity.noContent().build();
    }


    @Operation(summary = "Añade un producto a la lista de favoritos de un cliente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",
                    description = "Producto añadido a la lista de favoritos correctamente.",
                    content = @Content),
            @ApiResponse(responseCode = "401",
                    description = "No autorizado.",
                    content = @Content)
    })
    @PutMapping("/producto/{idProducto}")
    public ResponseEntity<?> addProducto(@AuthenticationPrincipal Cliente cliente, @PathVariable Long idProducto){
        clienteService.addProductoFavorito(cliente.getId(), idProducto);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Obtiene la lista de productos favoritos de un cliente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Lista de productos favoritos obtenida correctamente.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = GetProductoDto.class),
                            examples = {@ExampleObject(
                                    value = """
                                            [
                                                     {
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
                                                 ]
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "401",
                    description = "No autorizado.",
                    content = @Content)
    })
    @GetMapping("/producto")
    public Set<GetProductoDto> getFavoritos(@AuthenticationPrincipal Cliente cliente) {
        Set<Producto> productosFavoritos = clienteService.getProductoFavorito(cliente.getId());

        return productosFavoritos.stream()
                .map(GetProductoDto::of)
                .collect(Collectors.toSet());
    }

    @Operation(summary = "Elimina un producto de la lista de favoritos de un cliente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",
                    description = "Producto eliminado de la lista de favoritos correctamente.",
                    content = @Content),
            @ApiResponse(responseCode = "401",
                    description = "No autorizado.",
                    content = @Content)
    })
    @DeleteMapping("/producto/{idProducto}")
    public ResponseEntity<?> deleteProductoFavorito(@AuthenticationPrincipal Cliente cliente, @PathVariable Long idProducto){
        clienteService.deleteProductoFavorito(cliente.getId(), idProducto);
        return ResponseEntity.noContent().build();
    }

}
