package com.ivanwportfolio.arprog.Servicio;

import com.ivanwportfolio.arprog.Entidad.Proyecto;
import com.ivanwportfolio.arprog.Repositorio.IProyectoRepositorio;
import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
@Transactional
public class ImpProyectoServicio {
    @Autowired
    IProyectoRepositorio iProyectoRepositorio;
    
    public List<Proyecto> list(){
        return iProyectoRepositorio.findAll();
    }
    
    public Optional<Proyecto> getOne(int id){
        return iProyectoRepositorio.findById(id);
    }
    
    public Optional<Proyecto> getByNombreProy(String nombreProy){
        return iProyectoRepositorio.findByNombreProy(nombreProy);
    }
    
    public void save(Proyecto proy){
        iProyectoRepositorio.save(proy);
    }
    
    public void delete(int id){
        iProyectoRepositorio.deleteById(id);
    }
    
    public boolean existsById(int id){
        return iProyectoRepositorio.existsById(id);
    }
    
    public boolean existsByNombreProy(String nombreProy){
        return iProyectoRepositorio.existsByNombreProy(nombreProy);
    }
    
    public void guardarProyectoBD(MultipartFile archivo,String nombreProy, String detalleProy,String enlaceProy){
        Proyecto proyecto = new Proyecto();
        String nombreArchivo = StringUtils.cleanPath(archivo.getOriginalFilename());
        if(nombreArchivo.contains("..")){
            System.out.println("No es un archivo válido");
        }
        proyecto.setNombreProy(nombreProy);
        proyecto.setDetalleProy(detalleProy);
        proyecto.setEnlaceProy(enlaceProy);
        try {
            proyecto.setImagen(Base64.getEncoder().encodeToString(archivo.getBytes()));
        } catch (IOException ex) {
            Logger.getLogger(ImpProyectoServicio.class.getName()).log(Level.SEVERE, null, ex);
        }
        iProyectoRepositorio.save(proyecto);
    }
}    

