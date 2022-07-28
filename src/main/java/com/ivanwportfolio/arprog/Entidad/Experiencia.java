package com.ivanwportfolio.arprog.Entidad;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Experiencia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nombreExp;
    private String detalleExp;

    public Experiencia() {
    }

    public Experiencia(String nombreExp, String detalleExp) {
        this.nombreExp = nombreExp;
        this.detalleExp = detalleExp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreExp() {
        return nombreExp;
    }

    public void setNombreExp(String nombreExp) {
        this.nombreExp = nombreExp;
    }

    public String getDetalleExp() {
        return detalleExp;
    }

    public void setDetalleExp(String detalleExp) {
        this.detalleExp = detalleExp;
    }
    
    
}
