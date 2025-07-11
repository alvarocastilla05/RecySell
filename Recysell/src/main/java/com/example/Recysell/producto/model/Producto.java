package com.example.Recysell.producto.model;

import com.example.Recysell.categoria.model.Categoria;
import com.example.Recysell.cliente.model.Cliente;
import com.example.Recysell.donacion.model.Donacion;
import com.example.Recysell.util.SearchCriteria;
import com.example.Recysell.valora.model.Valora;
import jakarta.persistence.*;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.CascadeType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.criteria.Join;
import lombok.*;
import org.hibernate.annotations.*;
import org.hibernate.proxy.HibernateProxy;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;
import java.util.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(callSuper = true)
@Entity
@SQLDelete(sql = "UPDATE producto SET deleted = true WHERE id = ?")
@FilterDef(name = "deletedProductoFilter", parameters = @ParamDef(name = "isDeleted", type = Boolean.class))
@Filter(name = "deletedProductoFilter", condition = "deleted = :isDeleted")
public class Producto {

    @Id
    @GeneratedValue
    private Long id;

    private String nombre;

    private String descripcion;

    private double precio;

    private boolean disponibilidad;

    @ElementCollection
    @CollectionTable(name = "producto_imagenes", joinColumns = @JoinColumn(name = "producto_id"))
    @Builder.Default
    @Column(name = "imagen_url") // nombre de la columna para cada imagen
    private List<String> imagenes = new ArrayList<>();


    @Enumerated(EnumType.STRING)
    private Estado estado;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime fechaRegistro;


    private boolean deleted = Boolean.FALSE;

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

    @ManyToOne
    @JoinColumn(
            name = "categoria_id",
            foreignKey = @ForeignKey(name = "fk_producto_categoria")
    )
    private Categoria categoria;


    @OneToOne(mappedBy = "producto", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Valora valoracion;

    @OneToMany(mappedBy = "productoDonado", fetch = FetchType.LAZY)
    @Builder.Default
    @ToString.Exclude
    private List<Donacion> listaDonaciones = new ArrayList<>();



    //MÉTODOS HELPER

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

    // Filtro por categoría
    public static Specification<Producto> byCategoria(SearchCriteria criteria) {
        return (root, query, builder) -> {
            if (criteria.key().equalsIgnoreCase("categoria") && criteria.operation().equals(":")) {
                Join<Producto, Categoria> categoriaJoin = root.join("categoria");
                return builder.equal(categoriaJoin.get("nombre"), criteria.value().toString());
            }
            return null;
        };
    }

    // Filtro por rango de precio
    public static Specification<Producto> byRangoPrecio(SearchCriteria criteria) {
        return (root, query, builder) -> {
            if (!criteria.key().equalsIgnoreCase("precio")) {
                return null;
            }

            return switch (criteria.operation()) {
                case ">" -> builder.greaterThanOrEqualTo(root.get("precio"), Double.parseDouble(criteria.value().toString()));
                case "<" -> builder.lessThanOrEqualTo(root.get("precio"), Double.parseDouble(criteria.value().toString()));
                default -> null;
            };
        };
    }


    // Filtro por cliente vendedor
    public static Specification<Producto> byUsuario(SearchCriteria criteria) {
        return (root, query, builder) -> {
            if (criteria.key().equalsIgnoreCase("usuario") && criteria.operation().equals(":")) {
                Join<Producto, Cliente> clienteVendedorJoin = root.join("clienteVendedor");
                return builder.equal(clienteVendedorJoin.get("username"), criteria.value().toString());
            }
            return null;
        };
    }

    // Filtro por estado
    public static Specification<Producto> byEstado(SearchCriteria criteria) {
        return (root, query, builder) -> {
            if (criteria.key().equalsIgnoreCase("estado") && criteria.operation().equals(":")) {
                return builder.equal(root.get("estado"), Estado.valueOf(criteria.value().toString()));
            }
            return null;
        };
    }



}
