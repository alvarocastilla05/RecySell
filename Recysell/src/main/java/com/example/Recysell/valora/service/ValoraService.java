package com.example.Recysell.valora.service;

import com.example.Recysell.error.ProductoNotFoundException;
import com.example.Recysell.error.TrabajadorNotFoundException;
import com.example.Recysell.producto.model.Producto;
import com.example.Recysell.producto.repo.ProductoRepository;
import com.example.Recysell.trabajador.model.Trabajador;
import com.example.Recysell.trabajador.repo.TrabajadorRepository;
import com.example.Recysell.valora.dto.CreateValoraRequest;
import com.example.Recysell.valora.model.Valora;
import com.example.Recysell.valora.model.ValoraPK;
import com.example.Recysell.valora.repo.ValoraRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

@Service
@RequiredArgsConstructor
public class ValoraService {

    private final ValoraRepository valoraRepository;
    private final TrabajadorRepository trabajadorRepository;
    private final ProductoRepository productoRepository;

    public Valora save(CreateValoraRequest nuevo, Trabajador trabajador) {
        Producto producto = productoRepository.findById(nuevo.productoId())
                .orElseThrow(ProductoNotFoundException::new);


        // Construye la entidad Valora
        Valora valora = Valora.builder()
                .puntuacion(nuevo.puntuacion())
                .comentario(nuevo.comentario())
                .trabajadorValora(trabajador)
                .producto(producto)
                .valoraPK(new ValoraPK(trabajador.getId(), producto.getId()))
                .build();

        // Guarda la entidad
        return valoraRepository.save(valora);
    }
}
