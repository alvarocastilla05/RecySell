package com.example.Recysell.donacion.model;

import com.example.Recysell.organizacion.model.Organizacion;
import com.example.Recysell.producto.model.Producto;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Donacion {

    @EmbeddedId
    private DonacionPK donacionPK = new DonacionPK();

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime fechaDonacion;


    @ManyToOne
    @MapsId("productoId")
    @JoinColumn(name = "producto_id")
    private Producto productoDonado;

    @ManyToOne
    @MapsId("organizacionId")
    @JoinColumn(name = "organizacion_id")
    private Organizacion organizacion;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Donacion donacion = (Donacion) o;
        return getDonacionPK() != null && Objects.equals(getDonacionPK(), donacion.getDonacionPK());
    }

    @Override
    public final int hashCode() {
        return Objects.hash(donacionPK);
    }
}
