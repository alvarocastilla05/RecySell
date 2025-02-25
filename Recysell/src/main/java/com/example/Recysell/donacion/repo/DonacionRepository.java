package com.example.Recysell.donacion.repo;

import com.example.Recysell.donacion.dto.GetDonacionDto;
import com.example.Recysell.donacion.model.Donacion;
import com.example.Recysell.donacion.model.DonacionPK;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DonacionRepository extends JpaRepository<Donacion, DonacionPK> {

    @Query("""
        SELECT new com.example.Recysell.donacion.dto.GetDonacionDto(
            d.fechaDonacion,
            d.organizacion,
            d.productoDonado
            
        )
        FROM Donacion d
    """)
    Page<GetDonacionDto> findAllDonaciones(Pageable pageable);
}
