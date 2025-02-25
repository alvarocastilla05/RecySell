package com.example.Recysell.valora.service;

import com.example.Recysell.error.ProductoNotFoundException;
import com.example.Recysell.error.ProductoYaValoradoException;
import com.example.Recysell.error.TrabajadorNotFoundException;
import com.example.Recysell.error.ValoraNotFoundException;
import com.example.Recysell.producto.model.Producto;
import com.example.Recysell.producto.repo.ProductoRepository;
import com.example.Recysell.trabajador.model.Trabajador;
import com.example.Recysell.trabajador.repo.TrabajadorRepository;
import com.example.Recysell.valora.dto.CreateValoraRequest;
import com.example.Recysell.valora.dto.EditValoraCmd;
import com.example.Recysell.valora.dto.GetValoraDtoSinTrabajador;
import com.example.Recysell.valora.model.Valora;
import com.example.Recysell.valora.model.ValoraPK;
import com.example.Recysell.valora.repo.ValoraRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ValoraService {

    private final ValoraRepository valoraRepository;
    private final ProductoRepository productoRepository;
    private final TrabajadorRepository TrabajadorRepository;
    private final TrabajadorRepository trabajadorRepository;


    //Listar Valoraciones de un trabajador
    public Page<GetValoraDtoSinTrabajador> findAllByTrabajadorValora(UUID id, Pageable pageable) {
        Trabajador trabajadorValora = trabajadorRepository.findById(id)
                .orElseThrow(() -> new TrabajadorNotFoundException(id));

        Page<GetValoraDtoSinTrabajador> valoraciones = valoraRepository.findAllByTrabajadorValora(trabajadorValora, pageable);

        if(valoraciones.isEmpty()){
            throw new ValoraNotFoundException();
        }

        return valoraciones;
    }

    //Añadir valoración
    public Valora save(CreateValoraRequest nuevo, Trabajador trabajador) {
        Producto producto = productoRepository.findById(nuevo.productoId())
                .orElseThrow(() -> new ProductoNotFoundException(nuevo.productoId()));

        boolean exists = valoraRepository.existsByProductoId(producto.getId());
        if (exists) {
            throw new ProductoYaValoradoException();
        }

        Valora valora = Valora.builder()
                .puntuacion(nuevo.puntuacion())
                .comentario(nuevo.comentario())
                .trabajadorValora(trabajador)
                .producto(producto)
                .valoraPK(new ValoraPK(trabajador.getId(), producto.getId()))
                .build();

        return valoraRepository.save(valora);
    }

    //Editar Valoracion
    public Valora edit(Trabajador trabajador, EditValoraCmd editar, Long id){

        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new ProductoNotFoundException(id));

        return valoraRepository.findById(new ValoraPK(trabajador.getId(), id))
                .map(valora -> {
                    valora.setPuntuacion(editar.puntuacion());
                    valora.setComentario(editar.comentario());
                    return valoraRepository.save(valora);
                })
                .orElseThrow(() -> new ValoraNotFoundException());
    }

    //Eliminar Valoracion
    public void deleteById(Trabajador trabajador, Long id) {
        ValoraPK valoraPK = new ValoraPK(trabajador.getId(), id);

        // Buscar la entidad Valora usando el ValoraPK
        Valora valora = valoraRepository.findById(valoraPK)
                .orElseThrow(() -> new ValoraNotFoundException());

        // Eliminar la entidad encontrada
        valoraRepository.delete(valora);
    }

}
