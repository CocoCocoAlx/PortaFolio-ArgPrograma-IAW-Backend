package com.ivanwportfolio.arprog.Controladora;

import com.ivanwportfolio.arprog.Dto.ExperienciaDto;
import com.ivanwportfolio.arprog.Entidad.Experiencia;
import com.ivanwportfolio.arprog.Seguridad.Controladora.Mensaje;
import com.ivanwportfolio.arprog.Servicio.ImpExperienciaServicio;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("experiencialab")
public class ExperienciaControladora {

    @Autowired
    ImpExperienciaServicio impExperienciaServicio;

    @GetMapping("/lista")
    public ResponseEntity<List<Experiencia>> list() {
        List<Experiencia> list = impExperienciaServicio.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @PostMapping("/crear")
    public ResponseEntity<?> crear(@RequestBody ExperienciaDto experienciaDto) {
        if (StringUtils.isBlank(experienciaDto.getNombreExp())) {
            return new ResponseEntity(new Mensaje("El nombre es requerido"), HttpStatus.BAD_REQUEST);
        }
        if (impExperienciaServicio.existsByNombreExp(experienciaDto.getNombreExp())) {
            return new ResponseEntity(new Mensaje("Esta entrada ya existe"), HttpStatus.BAD_REQUEST);
        }

        Experiencia experiencia = new Experiencia(experienciaDto.getNombreExp(), experienciaDto.getDetalleExp());
        impExperienciaServicio.save(experiencia);

        return new ResponseEntity(new Mensaje("Experiencia cargada"), HttpStatus.BAD_REQUEST);

    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<?> actualizar(@PathVariable("id") int id, @RequestBody ExperienciaDto expDto) {
        if (!impExperienciaServicio.existsById(id)) {
            return new ResponseEntity(new Mensaje("El ID no existe"), HttpStatus.BAD_REQUEST);
        }

        if (impExperienciaServicio.existsByNombreExp(expDto.getNombreExp()) && impExperienciaServicio.getByNombreExp(expDto.getNombreExp()).get().getId() != id) {
            return new ResponseEntity(new Mensaje("La experiencia ya está cargada"), HttpStatus.BAD_REQUEST);
        }

        if (StringUtils.isBlank(expDto.getNombreExp())) {
            return new ResponseEntity(new Mensaje("El nombre es requerido"), HttpStatus.BAD_REQUEST);
        }

        Experiencia experiencia = impExperienciaServicio.getOne(id).get();
        experiencia.setNombreExp(expDto.getNombreExp());
        experiencia.setDetalleExp(expDto.getDetalleExp());
        impExperienciaServicio.save(experiencia);
        return new ResponseEntity(new Mensaje("Experiencia actualizada"), HttpStatus.OK);
    }

    public ResponseEntity<?> borrar(@PathVariable("id") int id) {
        if (!impExperienciaServicio.existsById(id)) {
            return new ResponseEntity(new Mensaje("El ID no existe"), HttpStatus.BAD_REQUEST);
        }

        impExperienciaServicio.delete(id);
        return new ResponseEntity(new Mensaje("Experiencia eliminada"), HttpStatus.OK);
    }
}
