package com.example.Recysell.valora.model;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class ValoraPK implements Serializable {

    private static final long serialVersionUID = 1L;

    private UUID trabajadorId;

    private UUID productoId;
}
