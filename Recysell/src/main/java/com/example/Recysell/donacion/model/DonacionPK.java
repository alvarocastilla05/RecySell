package com.example.Recysell.donacion.model;

import jakarta.persistence.Embeddable;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class DonacionPK implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long productoId;

    private Long organizacionId;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        DonacionPK that = (DonacionPK) o;
        return getProductoId() != null && Objects.equals(getProductoId(), that.getProductoId())
                && getOrganizacionId() != null && Objects.equals(getOrganizacionId(), that.getOrganizacionId());
    }

    @Override
    public final int hashCode() {
        return Objects.hash(productoId, organizacionId);
    }
}
