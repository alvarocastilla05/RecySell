package com.example.Recysell.cliente.model;

import com.example.Recysell.compra.model.Compra;
import com.example.Recysell.producto.model.Producto;
import com.example.Recysell.user.model.User;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;
import org.hibernate.annotations.SQLDelete;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@SuperBuilder
@Entity
@SQLDelete(sql = "UPDATE user_entity SET deleted = true WHERE id = ?") // Actualiza la tabla User
@FilterDef(name = "deletedClienteFilter", parameters = @ParamDef(name = "isDeleted", type = Boolean.class))
@Filter(name = "deletedClienteFilter", condition = "deleted = :isDeleted")
public class Cliente extends User {


    //Asociación con Compra.
    @OneToMany(mappedBy = "cliente", fetch = FetchType.LAZY)
    @Builder.Default
    @ToString.Exclude
    List<Compra> listaCompras = new ArrayList<>();

    //Asociación con Productos (Productos en venta).
    @OneToMany(mappedBy = "clienteVendedor", fetch = FetchType.LAZY)
    @Builder.Default
    @ToString.Exclude
    List<Producto> listProductosEnVenta = new ArrayList<>();

    //Asociación con Productos (Productos donados).
    @OneToMany(mappedBy = "clienteDonante", fetch = FetchType.LAZY)
    @Builder.Default
    @ToString.Exclude
    List<Producto> listaProductosDonados = new ArrayList<>();

    //Asociación con Productos (Favoritos).
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "anhade_favorito",
            joinColumns = @JoinColumn(name="cliente_id_favoritos"),
            inverseJoinColumns = @JoinColumn(name="producto_id_favorito"),
            foreignKey = @ForeignKey(name = "fk_cliente_producto_favorito"),
            inverseForeignKey = @ForeignKey(name = "fk_producto_favorito_cliente")
    )
    @Builder.Default
    @ToString.Exclude
    private Set<Producto> listaFavoritos = new HashSet<>();

    @PreUpdate
    @PreRemove
    private void handleSoftDelete() {
        if (this.isDeleted()) { // Verifica si el cliente está marcado como eliminado
            for (Producto producto : listProductosEnVenta) {
                producto.setDeleted(true); // Marca los productos como eliminados
            }
        }
    }


    //MÉTODOS HELPER

        //Con Productos (Productos en venta).

        public void addProductoEnVenta(Producto p){
            this.listProductosEnVenta.add(p);
            p.setClienteVendedor(this);
        }

        public void removeProductoEnVenta(Producto p){
            p.setClienteVendedor(null);
            this.listProductosEnVenta.remove(p);
        }

        //Con Productos (Productos Donados).

        public void addProductoDonado(Producto p){
            this.listaProductosDonados.add(p);
            p.setClienteDonante(this);
        }

        public void removeProductoDonado(Producto p){
            p.setClienteDonante(null);
            this.listaProductosDonados.remove(p);
        }

        //Con Productos (Favoritos).

        public void addProductoFavorito(Producto p){
            this.listaFavoritos.add(p);
            p.getListaClientes().add(this);
        }

        public void removeProductoFavorito(Producto p){
            p.getListaClientes().remove(this);
            this.listaFavoritos.remove(p);
        }
}
