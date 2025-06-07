package com.example.Recysell.lineaVenta.service;

import com.example.Recysell.error.LineaVentaNotFoundException;
import com.example.Recysell.files.service.StorageService;
import com.example.Recysell.lineaVenta.dto.GetLineaVentaDto;
import com.example.Recysell.lineaVenta.repo.LineaVentaRepository;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.hibernate.Filter;
import org.hibernate.Session;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LineaVentaService {

    private final LineaVentaRepository lineaVentaRepository;
    private final EntityManager entityManager;
    private final StorageService storageService;

    // Listar Líneas de Venta
    public Page<GetLineaVentaDto> findAll(Pageable pageable, boolean isDeleted) {
        Session session = entityManager.unwrap(Session.class);
        Filter filter = session.enableFilter("deletedLineaVentaFilter");
        filter.setParameter("isDeleted", isDeleted);

        Page<GetLineaVentaDto> result = lineaVentaRepository.findAllLineasVenta(pageable);
        session.disableFilter("deletedLineaVentaFilter");

        if (result.isEmpty()) {
            throw new LineaVentaNotFoundException();
        }
        return result;
    }


}
