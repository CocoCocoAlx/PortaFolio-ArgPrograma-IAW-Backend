package com.ivanwportfolio.arprog.Servicio;

import com.ivanwportfolio.arprog.Entidad.Habilidad;
import com.ivanwportfolio.arprog.Repositorio.IHabilidadRepositorio;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ImpHabilidadServicio {
    @Autowired
    IHabilidadRepositorio iHabilidadRepositorio;
    
        public List<Habilidad> list(){
        return iHabilidadRepositorio.findAll();
    }
    
    public Optional<Habilidad> getOne(int id){
        return iHabilidadRepositorio.findById(id);
    }
    
    public Optional<Habilidad> getByNombreHab(String nombreHab){
        return iHabilidadRepositorio.findByNombreHab(nombreHab);
    }
    
    public void save(Habilidad hab){
        iHabilidadRepositorio.save(hab);
    }
    
    public void delete(int id){
        iHabilidadRepositorio.deleteById(id);
    }
    
    public boolean existsById(int id){
        return iHabilidadRepositorio.existsById(id);
    }
    
    public boolean existsByNombreHab(String nombreHab){
        return iHabilidadRepositorio.existsByNombreHab(nombreHab);
    }

}
