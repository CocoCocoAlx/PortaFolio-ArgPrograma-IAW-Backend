package com.ivanwportfolio.arprog.Servicio;

import com.ivanwportfolio.arprog.Entidad.Persona;
import com.ivanwportfolio.arprog.Interface.IPersonaServicio;
import com.ivanwportfolio.arprog.Repositorio.IPersonaRepositorio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImpPersonaServicio implements IPersonaServicio{
    
    @Autowired IPersonaRepositorio ipersonarepositorio;

    @Override
    public List<Persona> getPersona() {
        List<Persona> persona = ipersonarepositorio.findAll();
        return persona;
    }

    @Override
    public void guardarPersona(Persona persona) {
        ipersonarepositorio.save(persona);
    }

    @Override
    public void eliminarPersona(int id) {
        ipersonarepositorio.deleteById(id);
    }

    @Override
    public Persona buscarPersona(int id) {
        Persona persona = ipersonarepositorio.findById(id).orElse(null);
        return persona;
    }
    
}
