package com.example.Recysell.lineaVenta.model;

import com.example.Recysell.compra.model.Compra;
import com.example.Recysell.producto.model.Producto;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;
import org.hibernate.annotations.SQLDelete;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(callSuper = true)
@Entity
@SQLDelete(sql = "UPDATE lineaVenta SET deleted = true WHERE id = ?")
@FilterDef(name = "deletedLineaVentaFilter", parameters = @ParamDef(name = "isDeleted", type = Boolean.class))
@Filter(name = "deletedLineaVentaFilter", condition = "deleted = :isDeleted")
public class LineaVenta {

    @Id
    @GeneratedValue
    private Long id;

    boolean deleted = Boolean.FALSE;

    @ManyToOne
    @JoinColumn(
            name = "compra_id",
            foreignKey = @ForeignKey(name = "fk_linea_venta_compra")
    )
    private Compra compra;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "producto_id_linea_venta")
    private Producto productoLinea;
}
