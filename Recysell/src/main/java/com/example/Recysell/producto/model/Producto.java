package com.example.Recysell.producto.model;

import com.example.Recysell.categoria.model.Categoria;
import com.example.Recysell.cliente.model.Cliente;
import com.example.Recysell.donacion.model.Donacion;
import com.example.Recysell.valora.model.Valora;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.util.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(callSuper = true)
@Entity
public class Producto {

    @Id
    @GeneratedValue
    private Long id;

    private String nombre;

    private String descripcion;

    private double precio;

    String imagen;

    //Asociación con Cliente (Productos en venta).
    @ManyToOne
    @JoinColumn(
            name = "cliente_id_vendedor",
            foreignKey = @ForeignKey(name = "fk_producto_cliente_vendedor")
    )
    private Cliente clienteVendedor;

    //Asociación con Cliente (Productos donados).
    @ManyToOne
    @JoinColumn(
            name = "cliente_id_donante",
            foreignKey = @ForeignKey(name = "fk_producto_cliente_donante")
    )
    private Cliente clienteDonante;

    //Asociación con Cliente (Favoritos).
    @ManyToMany(mappedBy = "listaFavoritos", fetch = FetchType.LAZY)
    @Builder.Default
    @ToString.Exclude
    private Set<Cliente> listaClientes = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "tiene",
            joinColumns = @JoinColumn(name="producto_id_categoria"),
            inverseJoinColumns = @JoinColumn(name="categoria_id_producto"),
            foreignKey = @ForeignKey(name = "fk_producto_categoria"),
            inverseForeignKey = @ForeignKey(name = "fk_categoria_producto")
    )
    @Builder.Default
    @ToString.Exclude
    private Set<Categoria> listaCategorias = new HashSet<>();

    @OneToOne(fetch = FetchType.LAZY)
    private Valora valoracion;

    @OneToMany(mappedBy = "productoDonado", fetch = FetchType.LAZY)
    @Builder.Default
    @ToString.Exclude
    private List<Donacion> listaDonaciones = new ArrayList<>();

    //MÉTODOS HELPER

        //Con Categoria.

        public void addCategoria(Categoria c){
            this.listaCategorias.add(c);
            c.getListaProductos().add(this);
        }

        public void removeCategoria(Categoria c){
            c.getListaProductos().remove(this);
            this.listaCategorias.remove(c);
        }

        //Con Cliente(Favoritos).

        public void addClienteFavorito(Cliente c){
            this.listaClientes.add(c);
            c.getListaFavoritos().add(this);
        }

        public void removeClienteFavorito(Cliente c){
            c.getListaFavoritos().remove(this);
            this.listaClientes.remove(c);
        }

        //Con Donacion.

        public void addDonacion(Donacion d){
            this.listaDonaciones.add(d);
            d.setProductoDonado(this);
        }

        public void removeDonacion(Donacion d){
            d.setProductoDonado(null);
            this.listaDonaciones.remove(d);
        }





    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Producto producto = (Producto) o;
        return getId() != null && Objects.equals(getId(), producto.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
