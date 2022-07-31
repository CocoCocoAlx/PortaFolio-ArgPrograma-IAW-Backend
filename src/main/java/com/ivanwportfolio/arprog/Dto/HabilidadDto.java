package com.ivanwportfolio.arprog.Dto;

import javax.validation.constraints.NotBlank;

public class HabilidadDto {

    @NotBlank
    private String nombreHab;
    @NotBlank
    private String valorHab;

    public HabilidadDto() {
    }

    public HabilidadDto(String nombreHab, String valorHab) {
        this.nombreHab = nombreHab;
        this.valorHab = valorHab;
    }

    public String getNombreHab() {
        return nombreHab;
    }

    public void setNombreHab(String nombreHab) {
        this.nombreHab = nombreHab;
    }

    public String getValorHab() {
        return valorHab;
    }

    public void setValorHab(String valorHab) {
        this.valorHab = valorHab;
    }

}