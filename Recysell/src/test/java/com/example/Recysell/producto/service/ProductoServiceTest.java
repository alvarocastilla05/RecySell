package com.example.Recysell.producto.service;

import com.example.Recysell.categoria.repo.CategoriaRepository;
import com.example.Recysell.cliente.model.Cliente;
import com.example.Recysell.error.ProductoNotFoundException;
import com.example.Recysell.files.model.FileMetadata;
import com.example.Recysell.files.service.StorageService;
import com.example.Recysell.producto.dto.CreateProductoDto;
import com.example.Recysell.producto.dto.EditProductoCmd;
import com.example.Recysell.producto.model.Producto;
import com.example.Recysell.producto.repo.ProductoRepository;
import jakarta.persistence.EntityManager;
import org.hibernate.Filter;
import org.hibernate.Session;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductoServiceTest {

    @Mock
    private ProductoRepository productoRepository;

    @Mock
    private StorageService storageService;

    @Mock
    private EntityManager entityManager;

    @Mock
    private CategoriaRepository categoriaRepository; // Este mock parece no ser usado, puedes eliminarlo si no lo necesitas

    @Mock
    private Session session;

    @Mock
    private Filter filter;

    @InjectMocks
    private ProductoService productoService;

    @BeforeEach
    void setUp() {
        // Usamos lenient para evitar la excepción si estos mocks no se usan en todos los tests
        lenient().when(entityManager.unwrap(Session.class)).thenReturn(session);
        lenient().when(session.enableFilter("deletedProductoFilter")).thenReturn(filter);
    }

    @Test
    void findById_ShouldReturnProducto_WhenExists() {
        Producto producto = new Producto();
        producto.setId(1L);
        producto.setDeleted(false);

        when(productoRepository.findById(1L)).thenReturn(Optional.of(producto));

        Producto result = productoService.findById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
    }

    @Test
    void findById_ShouldThrowException_WhenProductoDoesNotExist() {
        when(productoRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ProductoNotFoundException.class, () -> productoService.findById(1L));
    }

    @Test
    void save_ShouldReturnSavedProducto() {
        CreateProductoDto dto = new CreateProductoDto("Nombre", "Descripción", 100.0);
        Cliente cliente = new Cliente();
        MultipartFile file = mock(MultipartFile.class);

        // Usamos Mockito para crear un mock de FileMetadata
        FileMetadata fileMetadata = mock(FileMetadata.class);
        when(fileMetadata.getFilename()).thenReturn("imagen.jpg");

        when(storageService.store(file)).thenReturn(fileMetadata);
        when(productoRepository.save(any(Producto.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Producto result = productoService.save(dto, cliente, file);

        assertNotNull(result);
        assertEquals("Nombre", result.getNombre());
        assertEquals(100.0, result.getPrecio());
        assertEquals("imagen.jpg", result.getImagen());
        assertEquals(cliente, result.getClienteVendedor());
    }

    @Test
    void edit_ShouldUpdateProducto_WhenExists() {
        Producto producto = new Producto();
        producto.setId(1L);
        producto.setDeleted(false);

        EditProductoCmd cmd = new EditProductoCmd("Nuevo Nombre", "Nueva Descripción", 200.0);
        MultipartFile file = mock(MultipartFile.class);

        // Usamos Mockito para crear un mock de FileMetadata
        FileMetadata fileMetadata = mock(FileMetadata.class);
        when(fileMetadata.getFilename()).thenReturn("nueva_imagen.jpg");

        when(storageService.store(file)).thenReturn(fileMetadata);
        when(productoRepository.findById(1L)).thenReturn(Optional.of(producto));
        when(productoRepository.save(any(Producto.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Producto result = productoService.edit(cmd, 1L, file);

        assertNotNull(result);
        assertEquals("Nuevo Nombre", result.getNombre());
        assertEquals(200.0, result.getPrecio());
        assertEquals("nueva_imagen.jpg", result.getImagen());
    }

    @Test
    void edit_ShouldThrowException_WhenProductoDoesNotExist() {
        when(productoRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ProductoNotFoundException.class, () -> productoService.edit(new EditProductoCmd("Nombre", "Desc", 100.0), 1L, mock(MultipartFile.class)));
    }

    @Test
    void deleteById_ShouldCallRepository() {
        doNothing().when(productoRepository).deleteById(1L);

        productoService.deleteById(1L);

        verify(productoRepository, times(1)).deleteById(1L);
    }

    @Test
    void findAllProductosEnVenta_ShouldReturnPage_WhenProductosExist() {
        Page<Producto> productos = new PageImpl<>(List.of(new Producto(), new Producto()));
        Pageable pageable = mock(Pageable.class);
        Specification<Producto> spec = mock(Specification.class);

        when(productoRepository.findAll(spec, pageable)).thenReturn(productos);

        Page<Producto> result = productoService.findAllProductosEnVenta(pageable, false, spec);

        assertFalse(result.isEmpty());
        verify(session, times(1)).enableFilter("deletedProductoFilter");
        verify(filter, times(1)).setParameter("isDeleted", false);
        verify(session, times(1)).disableFilter("deletedProductoFilter");
    }

    @Test
    void findAllProductosEnVenta_ShouldThrowException_WhenNoProductosExist() {
        Pageable pageable = mock(Pageable.class);
        Specification<Producto> spec = mock(Specification.class);

        when(productoRepository.findAll(spec, pageable)).thenReturn(Page.empty());

        assertThrows(ProductoNotFoundException.class, () -> productoService.findAllProductosEnVenta(pageable, false, spec));

        verify(session, times(1)).enableFilter("deletedProductoFilter");
        verify(filter, times(1)).setParameter("isDeleted", false);
        verify(session, times(1)).disableFilter("deletedProductoFilter");
    }
}
