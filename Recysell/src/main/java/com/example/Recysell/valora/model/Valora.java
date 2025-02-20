package com.example.Recysell.valora.model;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Valora {

    @EmbeddedId
    private ValoraPK valoraPK = new ValoraPK();

    private int puntuacion;

    private String comentario;
}
