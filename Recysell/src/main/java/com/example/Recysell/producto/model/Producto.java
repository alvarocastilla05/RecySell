package com.example.Recysell.producto.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
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
}
