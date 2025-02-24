package com.example.Recysell.producto.service;

import com.example.Recysell.cliente.model.Cliente;
import com.example.Recysell.cliente.repo.ClienteRepository;
import com.example.Recysell.error.ClienteNotFoundException;
import com.example.Recysell.files.model.FileMetadata;
import com.example.Recysell.files.service.StorageService;
import com.example.Recysell.producto.dto.CreateProductoDto;
import com.example.Recysell.producto.model.Producto;
import com.example.Recysell.producto.repo.ProductoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class ProductoService {

    private final ProductoRepository productoRepository;
    private final ClienteRepository clienteRepository;
    private final StorageService storageService;

    //Añadir productos
    public Producto save(CreateProductoDto createProductoDto, Cliente authenticatedCliente, MultipartFile file) {
        FileMetadata fileMetadata = storageService.store(file);

        Producto producto = Producto.builder()
                .nombre(createProductoDto.nombre())
                .descripcion(createProductoDto.descripcion())
                .precio(createProductoDto.precio())
                .imagen(fileMetadata.getFilename())
                .clienteVendedor(authenticatedCliente) // Asigna el cliente autenticado
                .build();

        return productoRepository.save(producto);
    }


}
