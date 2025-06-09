package com.example.Recysell.compra.repo;

import com.example.Recysell.cliente.model.Cliente;
import com.example.Recysell.compra.dto.GetCompraDto;
import com.example.Recysell.compra.model.Compra;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface CompraRepository extends JpaRepository<Compra, Long> {

    @Query("""
    SELECT new com.example.Recysell.compra.dto.GetCompraDto(
        c.gastosEnvio,
        c.subTotal,
        c.fechaVenta,
        c.provincia,
        c.codigoPostal,
        c.direccionEntrega,
        c.cliente
    )
    FROM Compra c
    """)
    Page<GetCompraDto> findAllCompras(Pageable pageable);


    @Query("""
    SELECT new com.example.Recysell.compra.dto.GetCompraDto(c.gastosEnvio,
        c.subTotal,
        c.fechaVenta,
        c.provincia,
        c.codigoPostal,
        c.direccionEntrega,
        c.cliente)
    FROM Compra c
    WHERE c.cliente = :cliente
""")
    Page<GetCompraDto> findCompraByCliente(@Param("cliente") Cliente cliente, Pageable pageable);


}
