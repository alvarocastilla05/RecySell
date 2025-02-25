package com.example.Recysell.valora.repo;

import com.example.Recysell.trabajador.model.Trabajador;
import com.example.Recysell.valora.dto.GetValoraDtoSinTrabajador;
import com.example.Recysell.valora.model.Valora;
import com.example.Recysell.valora.model.ValoraPK;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ValoraRepository extends JpaRepository<Valora, ValoraPK> {


    Page<GetValoraDtoSinTrabajador> findAllByTrabajadorValora(Trabajador trabajadorValora, Pageable pageable);

    boolean existsByProductoId(Long id);
}
