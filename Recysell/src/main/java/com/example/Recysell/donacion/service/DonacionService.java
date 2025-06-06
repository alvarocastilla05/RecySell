package com.example.Recysell.donacion.service;

import com.example.Recysell.cliente.model.Cliente;
import com.example.Recysell.cliente.repo.ClienteRepository;
import com.example.Recysell.donacion.dto.EditDonacionCmd;
import com.example.Recysell.donacion.dto.GetDonacionDto;
import com.example.Recysell.donacion.model.Donacion;
import com.example.Recysell.donacion.model.DonacionPK;
import com.example.Recysell.donacion.repo.DonacionRepository;
import com.example.Recysell.error.*;
import com.example.Recysell.organizacion.model.Organizacion;
import com.example.Recysell.organizacion.repo.OrganizacionRepository;
import com.example.Recysell.producto.model.Producto;
import com.example.Recysell.producto.repo.ProductoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DonacionService {

    private final DonacionRepository donacionRepository;
    private final ProductoRepository productoRepository;
    private final OrganizacionRepository organizacionRepository;
    private final ClienteRepository clienteRepository;

    //Listar Donaciones
    public Page<GetDonacionDto> findAll(Pageable pageable){
        Page<GetDonacionDto> donaciones = donacionRepository.findAllDonaciones(pageable);

        if(donaciones.isEmpty()){
            throw new DonacionNotFoundException();
        }

        return donaciones;
    }

    //Donacion por ID
    public Donacion findById(DonacionPK id){
        Optional<Donacion> donacion = donacionRepository.findById(id);

        if(donacion.isPresent()){
            return donacion.get();
        }else {
            throw new DonacionNotFoundException();
        }
    }


    //Añadir Donacion
    @Transactional
    public Donacion save(EditDonacionCmd donacion, Cliente clienteAutenticado) {

        // Cargar cliente con la colección inicializada
        Cliente cliente = clienteRepository.findByIdWithProductosEnVenta(clienteAutenticado.getId())
                .orElseThrow(() -> new ClienteNotFoundException(clienteAutenticado.getId()));

        Producto producto = productoRepository.findById(donacion.productoId())
                .orElseThrow(() -> new ProductoNotFoundException(donacion.productoId()));

        Organizacion organizacion = organizacionRepository.findById(donacion.organizacionId())
                .orElseThrow(() -> new OrganizacionNotFoundException(donacion.organizacionId()));

        if (!producto.getClienteVendedor().getId().equals(cliente.getId())) {
            throw new UnauthorizedDonacionException("No puedes donar un producto que no has añadido");
        }

        // Modificar las listas
        cliente.removeProductoEnVenta(producto);
        cliente.addProductoDonado(producto);

        return donacionRepository.save(Donacion.builder()
                .donacionPK(new DonacionPK(producto.getId(), organizacion.getId()))
                .fechaDonacion(donacion.fechaDonacion())
                .productoDonado(producto)
                .organizacion(organizacion)
                .build());
    }

    //Eliminar Donacion
    public void delete(DonacionPK id){
        donacionRepository.deleteById(id);
    }

}
