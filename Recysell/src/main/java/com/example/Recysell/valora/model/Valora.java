package com.example.Recysell.valora.model;

import com.example.Recysell.producto.model.Producto;
import com.example.Recysell.trabajador.model.Trabajador;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.proxy.HibernateProxy;

import java.util.Objects;

@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Valora {

    @EmbeddedId
    private ValoraPK valoraPK = new ValoraPK();

    private int puntuacion;

    private String comentario;


    @OneToOne(cascade = CascadeType.PERSIST)
    @MapsId("productoId")
    @JoinColumn(name = "producto_id")
    private Producto producto;

    @ManyToOne
    @MapsId("trabajadorId")
    @JoinColumn(
            name = "trabajador_id",
            foreignKey = @ForeignKey(name = "fk_valora_trabajador")
    )
    private Trabajador trabajadorValora;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Valora valora = (Valora) o;
        return getValoraPK() != null && Objects.equals(getValoraPK(), valora.getValoraPK());
    }

    @Override
    public final int hashCode() {
        return Objects.hash(valoraPK);
    }
}
