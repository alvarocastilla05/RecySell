package com.example.Recysell.lineaVenta.model;

import com.example.Recysell.compra.model.Compra;
import com.example.Recysell.producto.model.Producto;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(callSuper = true)
@Entity
public class LineaVenta {

    @Id
    @GeneratedValue
    private Long id;

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
