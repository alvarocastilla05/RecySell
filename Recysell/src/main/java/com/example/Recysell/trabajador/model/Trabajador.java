package com.example.Recysell.trabajador.model;

import com.example.Recysell.organizacion.model.Organizacion;
import com.example.Recysell.user.model.User;
import com.example.Recysell.valora.model.Valora;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;
import org.hibernate.annotations.SQLDelete;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@SuperBuilder
@Entity
public class Trabajador extends User {

    private String puesto;

    @OneToMany(mappedBy = "trabajador" , fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @Builder.Default
    @ToString.Exclude
    private List<Organizacion> organizaciones = new ArrayList<>();

    @OneToMany(mappedBy = "trabajadorValora" , fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @Builder.Default
    @ToString.Exclude
    private List<Valora> valoraciones = new ArrayList<>();

    //MÉTODOS HELPER

    //Añadir organización a trabajador
    public void addOrganizacionTrabajador(Organizacion o){
        this.organizaciones.add(o);
        o.setTrabajador(this);
    }

    //Eliminar organización de trabajador

    public void removeOrganizacionTrabajador(Organizacion o){
        this.organizaciones.remove(o);
        o.setTrabajador(null);
    }

    //Añadir valoración a trabajador

    public void addValoracionTrabajador(Valora v){
        this.valoraciones.add(v);
        v.setTrabajadorValora(this);
    }

    //Eliminar valoración de trabajador

    public void removeValoracionTrabajador(Valora v){
        this.valoraciones.remove(v);
        v.setTrabajadorValora(null);
    }
}
