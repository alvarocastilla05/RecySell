package com.example.Recysell.cliente.model;

import com.example.Recysell.producto.model.Producto;
import com.example.Recysell.user.model.User;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@SuperBuilder
@Entity
public class Cliente extends User {

    @OneToMany(mappedBy = "cliente", fetch = FetchType.LAZY)
    @Builder.Default
    @ToString.Exclude
    List<Producto> listProductosEnVenta = new ArrayList<>();
}
