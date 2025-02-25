package com.example.Recysell.valora.model;

import jakarta.persistence.Embeddable;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class ValoraPK implements Serializable {

    private static final long serialVersionUID = 1L;

    private UUID trabajadorId;

    private Long productoId;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        ValoraPK valoraPK = (ValoraPK) o;
        return getTrabajadorId() != null && Objects.equals(getTrabajadorId(), valoraPK.getTrabajadorId())
                && getProductoId() != null && Objects.equals(getProductoId(), valoraPK.getProductoId());
    }

    @Override
    public final int hashCode() {
        return Objects.hash(trabajadorId, productoId);
    }
}
