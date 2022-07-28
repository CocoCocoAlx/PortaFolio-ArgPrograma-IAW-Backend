package com.ivanwportfolio.arprog.Servicio;

import com.ivanwportfolio.arprog.Entidad.Experiencia;
import com.ivanwportfolio.arprog.Repositorio.IExperienciaRepositorio;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ImpExperienciaServicio {
    @Autowired
    IExperienciaRepositorio iExperienciaRepositorio;
    
    public List<Experiencia> list(){
        return iExperienciaRepositorio.findAll();
    }
    
    public Optional<Experiencia> getOne(int id){
        return iExperienciaRepositorio.findById(id);
    }
    
    public Optional<Experiencia> getByNombreExp(String nombreExp){
        return iExperienciaRepositorio.findByNombreExp(nombreExp);
    }
    
    public void save(Experiencia exp){
        iExperienciaRepositorio.save(exp);
    }

    public void delete(int id){
        iExperienciaRepositorio.deleteById(id);
    }
    
    public boolean existsById(int id){
        return iExperienciaRepositorio.existsById(id);
    }
        
    public boolean existsByNombreExp(String nombreExp){
        return iExperienciaRepositorio.existsByNombreExp(nombreExp);
    }
    
    
}
