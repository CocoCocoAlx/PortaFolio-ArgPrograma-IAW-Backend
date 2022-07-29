package com.ivanwportfolio.arprog.Servicio;

import com.ivanwportfolio.arprog.Entidad.Estudios;
import com.ivanwportfolio.arprog.Repositorio.IEstudioRepositorio;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ImpEstudiosServicio {
    @Autowired
    IEstudioRepositorio iEstudioRepositorio;
    
    public List<Estudios> list(){
        return iEstudioRepositorio.findAll();
    }
    
    public Optional<Estudios> getOne(int id){
        return iEstudioRepositorio.findById(id);
    }
    
    public Optional<Estudios> getByNombreEst(String nombreEst){
        return iEstudioRepositorio.findByNombreEst(nombreEst);
    }
    
    public void save(Estudios est){
        iEstudioRepositorio.save(est);
    }

    public void delete(int id){
        iEstudioRepositorio.deleteById(id);
    }
    
    public boolean existsById(int id){
        return iEstudioRepositorio.existsById(id);
    }
        
    public boolean existsByNombreEst(String nombreEst){
        return iEstudioRepositorio.existsByNombreEst(nombreEst);
    }
    
    
}
