package com.ivanwportfolio.arprog.Controladora;

import com.ivanwportfolio.arprog.Entidad.Proyecto;
import com.ivanwportfolio.arprog.Seguridad.Controladora.Mensaje;
import com.ivanwportfolio.arprog.Servicio.ImpProyectoServicio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/proyectos")
public class ProyectoControlador {

    @Autowired
    ImpProyectoServicio impProyectoServicio;
    
    @GetMapping("/lista")
    public ResponseEntity<List<Proyecto>> list(){
        List<Proyecto> list = impProyectoServicio.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }
    
    @GetMapping("/detalle/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") int id){
        if(!impProyectoServicio.existsById(id))
            return new ResponseEntity(new Mensaje ("Proyecto no encontrado"),HttpStatus.NOT_FOUND);
        Proyecto proyecto = impProyectoServicio.getOne(id).get();
        return new ResponseEntity(proyecto, HttpStatus.OK);
    }
    @PostMapping("/crear")
    public ResponseEntity<?> guardarProducto(
    @RequestParam("archivo") MultipartFile archivo,
    @RequestParam("nombreProy") String nombreProy,
    @RequestParam("detalleProy") String detalleProy,
    @RequestParam("enlaceProy") String enlaceProy)
    {
        impProyectoServicio.guardarProyectoBD(archivo, nombreProy, detalleProy, enlaceProy);
        return new ResponseEntity(new Mensaje("Proyecto cargado"),HttpStatus.OK);
    }
    
    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<?> borrar(@PathVariable("id") int id){
        if (!impProyectoServicio.existsById(id)){
            return new ResponseEntity(new Mensaje("El ID no existe"),HttpStatus.BAD_GATEWAY);
        }
        impProyectoServicio.delete(id);
        return new ResponseEntity(new Mensaje("Proyecto eliminado"),HttpStatus.OK);
    }
}
