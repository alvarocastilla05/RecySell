package com.example.Recysell.producto.service;

import com.example.Recysell.cliente.model.Cliente;
import com.example.Recysell.cliente.repo.ClienteRepository;
import com.example.Recysell.error.ClienteNotFoundException;
import com.example.Recysell.error.ProductoNotFoundException;
import com.example.Recysell.files.model.FileMetadata;
import com.example.Recysell.files.service.StorageService;
import com.example.Recysell.producto.dto.CreateProductoDto;
import com.example.Recysell.producto.dto.GetProductoDto;
import com.example.Recysell.producto.model.Producto;
import com.example.Recysell.producto.repo.ProductoRepository;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.hibernate.Filter;
import org.hibernate.Session;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductoService {

    private final ProductoRepository productoRepository;
    private final StorageService storageService;
    private final EntityManager entityManager;


    //Listar Productos en Venta.
    public Page<GetProductoDto> findAllProductosEnVenta(Pageable pageable, boolean isDeleted){
        Session session = entityManager.unwrap(Session.class);
        Filter filter = session.enableFilter("deletedProductoFilter");
        filter.setParameter("isDeleted", isDeleted);

        Page<GetProductoDto> result = productoRepository.findAllProductosEnVenta(pageable);
        session.disableFilter("deletedProductoFilter");

        if (result.isEmpty()) {
            throw new ProductoNotFoundException();
        }

        return result;


    }

    //Buscar Producto por ID
    public Producto findById(Long id){
        Optional<Producto> producto = productoRepository.findById(id);

        if (producto.isPresent() && !producto.get().isDeleted()) {
            return producto.get();
        } else {
            throw new ProductoNotFoundException(id);
        }
    }


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
