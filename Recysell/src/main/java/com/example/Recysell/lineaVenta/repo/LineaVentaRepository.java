package com.example.Recysell.lineaVenta.repo;

import com.example.Recysell.lineaVenta.dto.GetLineaVentaDto;
import com.example.Recysell.lineaVenta.model.LineaVenta;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface LineaVentaRepository extends JpaRepository<LineaVenta, Long> {

    @Query("""
        SELECT new com.example.Recysell.lineaVenta.dto.GetLineaVentaDto(
            lv.productoLinea, lv.compra
        )
        FROM LineaVenta lv
    """)
    Page<GetLineaVentaDto> findAllLineasVenta(Pageable pageable);
}
