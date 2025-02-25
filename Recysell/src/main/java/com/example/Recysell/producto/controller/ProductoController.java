package com.example.Recysell.producto.controller;

import com.example.Recysell.categoria.dto.GetCategoriaDto;
import com.example.Recysell.cliente.dto.ClienteResponse;
import com.example.Recysell.cliente.dto.GetClienteDto;
import com.example.Recysell.cliente.model.Cliente;
import com.example.Recysell.cliente.service.ClienteService;
import com.example.Recysell.producto.dto.CreateProductoDto;
import com.example.Recysell.producto.dto.EditProductoCmd;
import com.example.Recysell.producto.dto.GetProductoConAsociaciones;
import com.example.Recysell.producto.dto.GetProductoDto;
import com.example.Recysell.producto.model.Producto;
import com.example.Recysell.producto.service.ProductoService;
import com.example.Recysell.query.ProductoSpecificationBuilder;
import com.example.Recysell.util.SearchCriteria;
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
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/producto")
public class ProductoController {

    private final ProductoService productoService;
    private final ClienteService clienteService;

    @Operation(summary = "Obtiene todos los productos en venta.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Lista de productos en venta.",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = GetProductoDto.class),
                            examples = {@ExampleObject(
                                    value = """
                                            {
                                                      "content": [
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
                                                      ],
                                                      "pageable": {
                                                          "pageNumber": 0,
                                                          "pageSize": 1,
                                                          "sort": {
                                                              "empty": true,
                                                              "sorted": false,
                                                              "unsorted": true
                                                          },
                                                          "offset": 0,
                                                          "paged": true,
                                                          "unpaged": false
                                                      },
                                                      "last": false,
                                                      "totalElements": 5,
                                                      "totalPages": 5,
                                                      "first": true,
                                                      "size": 1,
                                                      "number": 0,
                                                      "sort": {
                                                          "empty": true,
                                                          "sorted": false,
                                                          "unsorted": true
                                                      },
                                                      "numberOfElements": 1,
                                                      "empty": false
                                            }
                                            
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "No se encotró ningun producto.",
                    content = @Content),
    })
    @GetMapping("/listar")
    public Page<GetProductoDto> buscar(@PageableDefault Pageable pageable,
                                 @RequestParam(value = "search", required = false) String search,
                                 @RequestParam(value = "isDeleted", required = false) boolean isDeleted) {
        List<SearchCriteria> params = new ArrayList<>();

        if (search != null) {
            Pattern pattern = Pattern.compile("(\\w+?)(:|<|>)(\\w+?),");
            Matcher matcher = pattern.matcher(search + ",");

            while (matcher.find()) {
                String key = matcher.group(1);
                String operation = matcher.group(2);
                String value = matcher.group(3);

                params.add(new SearchCriteria(key, operation, value));
            }
        }

        ProductoSpecificationBuilder builder = new ProductoSpecificationBuilder(params);
        Specification<Producto> spec = builder.build();

        Page<Producto> productos = productoService.findAllProductosEnVenta(pageable, isDeleted, spec);

        return productos.map(GetProductoDto::of);

    }


    @Operation(summary = "Obtiene un producto por su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Producto encontrado.",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = GetProductoDto.class),
                            examples = {@ExampleObject(
                                    value = """
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
                                            }
                                            
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "Producto no encontrado.",
                    content = @Content),
            @ApiResponse(responseCode = "401",
                    description = "No autorizado.",
                    content = @Content),
    })
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('CLIENTE')")
    public GetProductoConAsociaciones findById(@PathVariable Long id) {

        Set<GetCategoriaDto> categorias = productoService.getCategoriasPorProducto(id);

        Producto producto = productoService.findById(id);
        return GetProductoConAsociaciones.of(producto, categorias);
    }

    @Operation(summary = "Registra un nuevo producto.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "El producto ha sido registrado correctamente.",
                    content = { @Content(mediaType = "application/form-data",
                            schema = @Schema(implementation = ClienteResponse.class),
                            examples = {@ExampleObject(
                                    value = """
                                            {
                                                "nombre": "Ordenador",
                                                "descripcion": "chulo chulo",
                                                "precio": 155.0
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
    public ResponseEntity<GetProductoDto> createProducto(@Valid @RequestPart("producto") CreateProductoDto createProductoDto,
                                                         @AuthenticationPrincipal Cliente authenticatedCliente, @RequestPart("file") MultipartFile file) {
        Producto producto = productoService.save(createProductoDto, authenticatedCliente, file);
        return ResponseEntity.status(HttpStatus.CREATED).body(GetProductoDto.of(producto));


    }


    @Operation(summary = "Actualiza un producto.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "El producto ha sido actualizado correctamente.",
                    content = { @Content(mediaType = "application/form-data",
                            schema = @Schema(implementation = GetProductoDto.class),
                            examples = {@ExampleObject(
                                    value = """
                                            {
                                                "nombre": "Ordenador",
                                                "descripcion": "chulo chulo",
                                                "precio": 155.0
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
                    description = "Producto no encontrado.",
                    content = @Content),
            @ApiResponse(responseCode = "403",
                    description = "No autorizado para modificar el producto.",
                    content = @Content),
    })
    @PutMapping("/{id}")
    @PreAuthorize("@productoService.esPropietario(#id, authentication.principal.username)")
    public ResponseEntity<GetProductoDto> updateProducto(@PathVariable Long id, @Valid @RequestPart("producto")EditProductoCmd editProductoCmd, @RequestPart("file") MultipartFile file) {
        Producto producto = productoService.edit(editProductoCmd, id, file);
        return ResponseEntity.ok(GetProductoDto.of(producto));
    }

    @Operation(summary = "Elimina un producto.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",
                    description = "El producto ha sido eliminado correctamente.",
                    content = @Content),
            @ApiResponse(responseCode = "401",
                    description = "No autorizado.",
                    content = @Content),
            @ApiResponse(responseCode = "403",
                    description = "No autorizado para eliminar el producto.",
                    content = @Content),
    })
    @DeleteMapping("/{id}")
    @PreAuthorize("@productoService.esPropietario(#id, authentication.principal.username)")
    public ResponseEntity<?> deleteProducto(@PathVariable Long id) {
        productoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Obtiene los productos de un cliente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Lista de productos del cliente.",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = GetProductoDto.class),
                            examples = {@ExampleObject(
                                    value = """
                                            {
                                                      "content": [
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
                                                        ],
                                                        "pageable": {
                                                            "pageNumber": 0,
                                                            "pageSize": 1,
                                                            "sort": {
                                                                "empty": true,
                                                                "sorted": false,
                                                                "unsorted": true
                                                            },
                                                            "offset": 0,
                                                            "paged": true,
                                                            "unpaged": false
                                                        },
                                                        "last": false,
                                                        "totalElements": 5,
                                                        "totalPages": 5,
                                                        "first": true,
                                                        "size": 1,
                                                        "number": 0,
                                                        "sort": {
                                                            "empty": true,
                                                            "sorted": false,
                                                            "unsorted": true
                                                        },
                                                        "numberOfElements": 1,
                                                        "empty": false
                                            }
                                            
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "No se encotró ningun producto.",
                    content = @Content),
    })
    @GetMapping("/cliente/{id}")
    public List<GetProductoDto> findProductosByClienteVendedor(@PathVariable UUID id, Pageable pageable) {

        Cliente cliente = clienteService.findById(id);
        return productoService.findProductosByClienteVendedor(cliente, pageable).getContent();
    }

    @Operation(summary = "Añade una categoría a un producto.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",
                    description = "La categoría ha sido añadida correctamente.",
                    content = @Content),
            @ApiResponse(responseCode = "404",
                    description = "Producto o categoría no encontrados.",
                    content = @Content),
            @ApiResponse(responseCode = "401",
                    description = "No autorizado.",
                    content = @Content),
    })
    @PutMapping("/{idProducto}/categoria/{idCategoria}")
    public ResponseEntity<?> addCategoria(@PathVariable Long idProducto, @PathVariable Long idCategoria){
        productoService.addCategoriaToProducto(idProducto, idCategoria);
        return ResponseEntity.noContent().build();
    }
}
