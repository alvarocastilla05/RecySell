package com.example.Recysell.producto.service;

import com.example.Recysell.categoria.dto.GetCategoriaDto;
import com.example.Recysell.categoria.model.Categoria;
import com.example.Recysell.categoria.repo.CategoriaRepository;
import com.example.Recysell.categoria.service.CategoriaService;
import com.example.Recysell.cliente.model.Cliente;
import com.example.Recysell.cliente.repo.ClienteRepository;
import com.example.Recysell.error.CategoriaNotFoundException;
import com.example.Recysell.error.ClienteNotFoundException;
import com.example.Recysell.error.ProductoNotFoundException;
import com.example.Recysell.files.model.FileMetadata;
import com.example.Recysell.files.service.StorageService;
import com.example.Recysell.producto.dto.CreateProductoDto;
import com.example.Recysell.producto.dto.EditProductoCmd;
import com.example.Recysell.producto.dto.GetProductoDto;
import com.example.Recysell.producto.model.Producto;
import com.example.Recysell.producto.repo.ProductoRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.hibernate.Filter;
import org.hibernate.Session;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductoService {

    private final ProductoRepository productoRepository;
    private final StorageService storageService;
    private final EntityManager entityManager;
    private final CategoriaRepository categoriaRepository;
    private final CategoriaService categoriaService;


    public Set<GetProductoDto> getProductoDtosByCategoria(Long id){
        return productoRepository.findProductosByCategoria(id);
    }

    public boolean esPropietario(Long productoId, String username) {
        return productoRepository.existsByIdAndClienteVendedor_Username(productoId, username);
    }




    //Listar Productos en Venta.
    public Page<Producto> findAllProductosEnVenta(Pageable pageable, boolean isDeleted, Specification<Producto> spec) {
        // Obtener la sesión de Hibernate y activar el filtro de eliminación
        Session session = entityManager.unwrap(Session.class);
        Filter filter = session.enableFilter("deletedProductoFilter");
        filter.setParameter("isDeleted", isDeleted);

        // Si se ha proporcionado una especificación, la utilizamos con findAll
        Page<Producto> result = productoRepository.findAll(spec, pageable);

        // Desactivar el filtro de eliminación después de la consulta
        session.disableFilter("deletedProductoFilter");

        // Verificar si el resultado está vacío y lanzar una excepción si es necesario
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


    //Añadir producto
    @Transactional
    public Producto save(CreateProductoDto createProductoDto, Cliente authenticatedCliente, List<MultipartFile> files) {
        List<String> nombresImagenes = files.stream()
                .map(file -> storageService.store(file).getFilename())
                .toList();

        Categoria categoria = categoriaRepository.findById(createProductoDto.categoriaId())
                .orElseThrow(() -> new CategoriaNotFoundException(createProductoDto.categoriaId()));

        Producto producto = Producto.builder()
                .nombre(createProductoDto.nombre())
                .descripcion(createProductoDto.descripcion())
                .precio(createProductoDto.precio())
                .imagenes(nombresImagenes) // lista de strings
                .estado(createProductoDto.estado())
                .categoria(categoria)
                .clienteVendedor(authenticatedCliente)
                .build();

        return productoRepository.save(producto);
    }



    //Editar Producto
    @Transactional
    public Producto edit(EditProductoCmd editProductoCmd, Long id, List<MultipartFile> files) {
        Producto producto = productoRepository.findById(id)
                .filter(p -> !p.isDeleted())
                .orElseThrow(() -> new ProductoNotFoundException(id));

        Categoria categoria = categoriaRepository.findById(editProductoCmd.categoriaId())
                .orElseThrow(() -> new CategoriaNotFoundException(editProductoCmd.categoriaId()));

        // Subir y guardar las nuevas imágenes
        List<String> nombresImagenes = files.stream()
                .map(file -> storageService.store(file).getFilename())
                .toList();

        // Actualizar el producto
        producto.setNombre(editProductoCmd.nombre());
        producto.setDescripcion(editProductoCmd.descripcion());
        producto.setPrecio(editProductoCmd.precio());
        producto.setEstado(editProductoCmd.estado());
        producto.setCategoria(categoria);
        producto.setImagenes(nombresImagenes); // <-- aquí guardas múltiples imágenes

        return productoRepository.save(producto);
    }


    //Eliminar Producto
    public void deleteById(Long id){
        productoRepository.deleteById(id);
    }

    //Lista de productos por cliente
    public Page<GetProductoDto> findProductosByClienteVendedor(Cliente cliente, Pageable pageable){
        return productoRepository.findProductosByClienteVendedor(cliente, pageable)
                .map(GetProductoDto::of);
    }

    //Añadir categoria a producto
    /*public void addCategoriaToProducto(Long productoId, Long CategoriaId){
        Optional<Producto> producto = productoRepository.findById(productoId);
        Optional<Categoria> categoria = categoriaRepository.findById(CategoriaId);

        if(producto.isPresent() && categoria.isPresent() && !producto.get().isDeleted() && !categoria.get().isDeleted()){
            producto.get().addCategoria(categoria.get());
            productoRepository.save(producto.get());
        }else{
            throw new ProductoNotFoundException(productoId);
        }
    }*/


}
