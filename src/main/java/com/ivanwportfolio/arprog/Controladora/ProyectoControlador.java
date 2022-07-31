package com.ivanwportfolio.arprog.Controladora;

import com.ivanwportfolio.arprog.Dto.proyectosDto;
import com.ivanwportfolio.arprog.Entidad.Proyecto;
import com.ivanwportfolio.arprog.Seguridad.Controladora.Mensaje;
import com.ivanwportfolio.arprog.Servicio.ImpProyectoServicio;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "https://argprogramaiawfront.web.app")
@RequestMapping("/proyectos")
public class ProyectoControlador {

    @Autowired
    ImpProyectoServicio impProyectoServicio;

    @GetMapping("/lista")
    public ResponseEntity<List<Proyecto>> list() {
        List<Proyecto> list = impProyectoServicio.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping("/detalle/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") int id) {
        if (!impProyectoServicio.existsById(id)) {
            return new ResponseEntity(new Mensaje("Proyecto no encontrado"), HttpStatus.NOT_FOUND);
        }
        Proyecto proyecto = impProyectoServicio.getOne(id).get();
        return new ResponseEntity(proyecto, HttpStatus.OK);
    }

    @PostMapping("/crear")
    public ResponseEntity<?> crear(@RequestBody proyectosDto proyectoDto) {
        if (StringUtils.isBlank(proyectoDto.getNombreProy())) {
            return new ResponseEntity(new Mensaje("El nombre es requerido"), HttpStatus.BAD_REQUEST);
        }
        if (impProyectoServicio.existsByNombreProy(proyectoDto.getNombreProy())) {
            return new ResponseEntity(new Mensaje("Esta entrada ya existe"), HttpStatus.BAD_REQUEST);
        }

        Proyecto proyecto = new Proyecto(proyectoDto.getNombreProy(), proyectoDto.getDetalleProy(), proyectoDto.getEnlaceProy(), proyectoDto.getImagen());
        impProyectoServicio.save(proyecto);

        return new ResponseEntity(new Mensaje("Experiencia cargada"), HttpStatus.OK);

    }

    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<?> borrar(@PathVariable("id") int id) {
        if (!impProyectoServicio.existsById(id)) {
            return new ResponseEntity(new Mensaje("El ID no existe"), HttpStatus.BAD_GATEWAY);
        }
        impProyectoServicio.delete(id);
        return new ResponseEntity(new Mensaje("Proyecto eliminado"), HttpStatus.OK);
    }
    
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<?> actualizar(@PathVariable("id") int id, @RequestBody proyectosDto proyectoDto) {
        if (!impProyectoServicio.existsById(id)) {
            return new ResponseEntity(new Mensaje("El ID no existe"), HttpStatus.BAD_REQUEST);
        }

        if (impProyectoServicio.existsByNombreProy(proyectoDto.getNombreProy()) && impProyectoServicio.getByNombreProy(proyectoDto.getNombreProy()).get().getId() != id) {
            return new ResponseEntity(new Mensaje("Este proyecto ya está cargado"), HttpStatus.BAD_REQUEST);
        }

        if (StringUtils.isBlank(proyectoDto.getNombreProy())) {
            return new ResponseEntity(new Mensaje("El nombre es requerido"), HttpStatus.BAD_REQUEST);
        }

        Proyecto proyecto = impProyectoServicio.getOne(id).get();
        proyecto.setNombreProy(proyectoDto.getNombreProy());
        proyecto.setDetalleProy(proyectoDto.getDetalleProy());
        impProyectoServicio.save(proyecto);
        return new ResponseEntity(new Mensaje("Proyecto actualizado"), HttpStatus.OK);
    }
}
