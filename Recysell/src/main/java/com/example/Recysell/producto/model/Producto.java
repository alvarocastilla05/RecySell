package com.example.Recysell.producto.model;

import com.example.Recysell.cliente.model.Cliente;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity
public class Producto {

    @Id
    @GeneratedValue
    private UUID id;

    private String nombre;

    private String descripcion;

    private double precio;

    //Asociación con Cliente.
    @ManyToOne
    @JoinColumn(
            name = "cliente_id_vende",
            foreignKey = @ForeignKey(name = "fk_producto_cliente_vende")
    )
    private Cliente cliente;
}
