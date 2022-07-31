package com.ivanwportfolio.arprog.Entidad;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Estudios {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nombreEst;
    private String detalleEst;
    private String periodoEst;

    public Estudios() {
    }

    public Estudios(String nombreEst, String detalleEst, String periodoEst) {
        this.nombreEst = nombreEst;
        this.detalleEst = detalleEst;
        this.periodoEst = periodoEst;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreEst() {
        return nombreEst;
    }

    public void setNombreEst(String nombreEst) {
        this.nombreEst = nombreEst;
    }

    public String getDetalleEst() {
        return detalleEst;
    }

    public void setDetalleEst(String detalleEst) {
        this.detalleEst = detalleEst;
    }

    public String getPeriodoEst() {
        return periodoEst;
    }

    public void setPeriodoEst(String periodoEst) {
        this.periodoEst = periodoEst;
    }
    
    
}
