package com.ivanwportfolio.arprog.Dto;

import javax.validation.constraints.NotBlank;


public class EstudiosDto {
    @NotBlank
    private String nombreEst;
    @NotBlank
    private String detalleEst;

    public EstudiosDto() {
    }

    public EstudiosDto(String nombreEst, String detalleEst) {
        this.nombreEst = nombreEst;
        this.detalleEst = detalleEst;
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
    
    
}
