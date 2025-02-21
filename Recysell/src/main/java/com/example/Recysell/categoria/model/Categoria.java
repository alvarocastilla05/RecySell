package com.example.Recysell.categoria.model;

import com.example.Recysell.producto.model.Producto;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Categoria {

    @Id
    @GeneratedValue
    private UUID id;

    private String nombre;

    @ManyToMany(mappedBy = "listaCategorias", fetch = FetchType.LAZY)
    @Builder.Default
    @ToString.Exclude
    private Set<Producto> listaProductos = new HashSet<>();

    //MÉTODOS HELPER

    public void addProducto(Producto p){
        this.listaProductos.add(p);
        p.getListaCategorias().add(this);
    }

    public void removeProducto(Producto p){
        this.listaProductos.remove(p);
        p.getListaCategorias().remove(this);
    }
}
