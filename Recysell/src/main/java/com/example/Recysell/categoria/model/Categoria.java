package com.example.Recysell.categoria.model;

import com.example.Recysell.producto.model.Producto;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.proxy.HibernateProxy;

import java.util.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@SQLDelete(sql = "UPDATE categoria SET deleted = true WHERE id = ?")
@FilterDef(name = "deletedCategoriaFilter", parameters = @ParamDef(name = "isDeleted", type = Boolean.class))
@Filter(name = "deletedCategoriaFilter", condition = "deleted = :isDeleted")
public class Categoria {

    @Id
    @GeneratedValue
    private Long id;

    private String nombre;

    private String imagen;

    boolean deleted = Boolean.FALSE;

    @OneToMany(mappedBy = "categoria", fetch = FetchType.LAZY)
    @Builder.Default
    @ToString.Exclude
    private List<Producto> listaProductos = new ArrayList<>();


    //MÉTODOS HELPER

    public void addProducto(Producto p){
        this.listaProductos.add(p);
        p.setCategoria(this);
    }

    public void removeProducto(Producto p){
        p.setCategoria(null);
        this.listaProductos.remove(p);
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Categoria categoria = (Categoria) o;
        return getId() != null && Objects.equals(getId(), categoria.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
