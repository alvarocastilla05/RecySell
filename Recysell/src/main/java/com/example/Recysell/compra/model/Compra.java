package com.example.Recysell.compra.model;

import com.example.Recysell.cliente.model.Cliente;
import com.example.Recysell.lineaVenta.model.LineaVenta;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

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
public class Compra {

    @Id
    @GeneratedValue
    private Long id;

    private double gastosEnvio;

    private double subTotal;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime fechaVenta;

    private String provincia;

    private String codigoPostal;

    private String direccionEntrega;

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

}
