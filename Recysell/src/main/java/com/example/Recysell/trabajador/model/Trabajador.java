package com.example.Recysell.trabajador.model;

import com.example.Recysell.user.model.User;
import jakarta.persistence.Entity;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
public class Trabajador extends User {

    private String puesto;
}
