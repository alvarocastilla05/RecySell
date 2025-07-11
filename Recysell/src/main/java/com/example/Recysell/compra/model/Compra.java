package com.example.Recysell.compra.model;

import com.example.Recysell.cliente.model.Cliente;
import com.example.Recysell.lineaVenta.model.LineaVenta;
import com.example.Recysell.producto.model.Estado;
import jakarta.persistence.*;
import jakarta.persistence.ForeignKey;
import lombok.*;
import org.hibernate.annotations.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(callSuper = true)
@Entity
@SQLDelete(sql = "UPDATE compra SET deleted = true WHERE id = ?")
@FilterDef(name = "deletedCompraFilter", parameters = @ParamDef(name = "isDeleted", type = Boolean.class))
@Filter(name = "deletedCompraFilter", condition = "deleted = :isDeleted")
public class Compra {

    @Id
    @GeneratedValue
    private Long id;

    private boolean deleted = Boolean.FALSE;

    private double gastosEnvio;

    private double subTotal;

    private LocalDateTime fechaVenta;

    private LocalDateTime fechaEntrega;

    private String provincia;

    private String codigoPostal;

    private String direccionEntrega;

    @Enumerated(EnumType.STRING)
    private EstadoCompra estadoCompra;

    //Asociacion con Cliente
    @ManyToOne
    @JoinColumn(
            name = "cliente_id",
            foreignKey = @ForeignKey(name = "fk_compra_cliente")
    )
    private Cliente cliente;

    @OneToMany(mappedBy = "compra", fetch = FetchType.LAZY)
    @Builder.Default
    @ToString.Exclude
    private List<LineaVenta> lineaVentas = new ArrayList<>();

    //MÉTODOS HELPER

    //Línea de venta
    public void addLineaVenta(LineaVenta lineaVenta) {
        this.lineaVentas.add(lineaVenta);
        lineaVenta.setCompra(this);
    }

    public void removeLineaVenta(LineaVenta lineaVenta) {
        lineaVenta.setCompra(null);
        this.lineaVentas.remove(lineaVenta);
    }

}
