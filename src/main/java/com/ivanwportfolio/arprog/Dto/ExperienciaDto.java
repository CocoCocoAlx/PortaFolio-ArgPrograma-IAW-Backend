package com.ivanwportfolio.arprog.Dto;

import javax.validation.constraints.NotBlank;


public class ExperienciaDto {
    @NotBlank
    private String nombreExp;
    @NotBlank
    private String detalleExp;
    @NotBlank
    private String periodoExp;

    public ExperienciaDto() {
    }

    public ExperienciaDto(String nombreExp, String detalleExp, String periodoExp) {
        this.nombreExp = nombreExp;
        this.detalleExp = detalleExp;
        this.periodoExp=periodoExp;
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

    public String getPeriodoExp() {
        return periodoExp;
    }

    public void setPeriodoExp(String periodoExp) {
        this.periodoExp = periodoExp;
    }
    
    
}
