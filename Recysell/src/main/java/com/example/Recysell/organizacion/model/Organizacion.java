package com.example.Recysell.organizacion.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Entity
public class Organizacion {

    @Id
    @GeneratedValue
    private UUID id;

    private String nombre;

    private String direccion;
}
