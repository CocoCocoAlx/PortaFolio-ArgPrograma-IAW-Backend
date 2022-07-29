package com.ivanwportfolio.arprog.Dto;

import javax.validation.constraints.NotBlank;

public class proyectosDto {
    @NotBlank
    private String nombreProy;
    @NotBlank
    private String detalleProy;
    @NotBlank
    private String enlaceProy;
    private String imagen;

    public proyectosDto() {
    }

    public proyectosDto(String nombreProy, String detalleProy, String enlaceProy) {
        this.nombreProy = nombreProy;
        this.detalleProy = detalleProy;
        this.enlaceProy = enlaceProy;
    }

    public String getNombreProy() {
        return nombreProy;
    }

    public void setNombreProy(String nombreProy) {
        this.nombreProy = nombreProy;
    }

    public String getDetalleProy() {
        return detalleProy;
    }

    public void setDetalleProy(String detalleProy) {
        this.detalleProy = detalleProy;
    }

    public String getEnlaceProy() {
        return enlaceProy;
    }

    public void setEnlaceProy(String enlaceProy) {
        this.enlaceProy = enlaceProy;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
    
    
}
