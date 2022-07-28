package com.ivanwportfolio.arprog.Dto;

import javax.validation.constraints.NotBlank;


public class ExperienciaDto {
    @NotBlank
    private String nombreExp;
    @NotBlank
    private String detalleExp;

    public ExperienciaDto() {
    }

    public ExperienciaDto(String nombreExp, String detalleExp) {
        this.nombreExp = nombreExp;
        this.detalleExp = detalleExp;
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
