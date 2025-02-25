package com.example.Recysell.organizacion.model;

import com.example.Recysell.donacion.model.Donacion;
import com.example.Recysell.trabajador.model.Trabajador;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.proxy.HibernateProxy;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Builder
@Entity
@SQLDelete(sql = "UPDATE organizacion SET deleted = true WHERE id = ?")
@FilterDef(name = "deletedOrganizacionFilter", parameters = @ParamDef(name = "isDeleted", type = Boolean.class))
@Filter(name = "deletedOrganizacionFilter", condition = "deleted = :isDeleted")
public class Organizacion {

    @Id
    @GeneratedValue
    private Long id;

    private String nombre;

    private String direccion;

    private boolean deleted = Boolean.FALSE;

    @ManyToOne
    @JoinColumn(
            name = "trabajador_id",
            foreignKey = @ForeignKey(name = "fk_organizacion_trabajador")
    )
    private Trabajador trabajador;

    @OneToMany(mappedBy = "organizacion", fetch = FetchType.LAZY)
    @Builder.Default
    @ToString.Exclude
    private List<Donacion> listaDonaciones = new ArrayList<>();

    //MÉTODOS HELPER

    public void addOrganizacionDonacion(Donacion d){
        this.listaDonaciones.add(d);
        d.setOrganizacion(this);
    }

    public void removeOrganizacionDonacion(Donacion d){
        d.setOrganizacion(null);
        this.listaDonaciones.remove(d);
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Organizacion that = (Organizacion) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
