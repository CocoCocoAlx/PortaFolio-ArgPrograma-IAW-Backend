package com.ivanwportfolio.arprog.Interface;

import com.ivanwportfolio.arprog.Entidad.Persona;
import java.util.List;

public interface IPersonaServicio {
    public List<Persona> getPersona();
    public void guardarPersona(Persona persona);
    public void eliminarPersona(int id);
    public Persona buscarPersona(int id);
}
