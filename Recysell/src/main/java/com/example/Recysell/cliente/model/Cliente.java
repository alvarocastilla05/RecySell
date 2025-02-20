package com.example.Recysell.cliente.model;

import com.example.Recysell.user.model.User;
import jakarta.persistence.Entity;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@ToString
@AllArgsConstructor
@SuperBuilder
@Entity
public class Cliente extends User {
}
