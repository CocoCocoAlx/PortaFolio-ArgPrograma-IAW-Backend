package com.ivanwportfolio.arprog.Dto;

import javax.validation.constraints.NotBlank;


public class EstudiosDto {
    @NotBlank
    private String nombreEst;
    @NotBlank
    private String detalleEst;
    @NotBlank
    private String periodoEst;

    public EstudiosDto() {
    }

    public EstudiosDto(String nombreEst, String detalleEst, String periodoEst) {
        this.nombreEst = nombreEst;
        this.detalleEst = detalleEst;
        this.periodoEst = periodoEst;
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
