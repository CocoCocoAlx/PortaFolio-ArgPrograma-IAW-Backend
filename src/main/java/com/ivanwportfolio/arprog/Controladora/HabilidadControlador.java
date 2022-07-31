package com.ivanwportfolio.arprog.Controladora;

import com.ivanwportfolio.arprog.Dto.HabilidadDto;
import com.ivanwportfolio.arprog.Entidad.Habilidad;
import com.ivanwportfolio.arprog.Seguridad.Controladora.Mensaje;
import com.ivanwportfolio.arprog.Servicio.ImpHabilidadServicio;
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
@RequestMapping("/habilidades")
public class HabilidadControlador {

    @Autowired
    ImpHabilidadServicio impHabilidadServicio;
    
    @GetMapping("/lista")
    public ResponseEntity<List<Habilidad>> list() {
        List<Habilidad> list = impHabilidadServicio.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping("/detalle/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") int id) {
        if (!impHabilidadServicio.existsById(id)) {
            return new ResponseEntity(new Mensaje("Habilidad no encontrada"), HttpStatus.NOT_FOUND);
        }
        Habilidad habilidad = impHabilidadServicio.getOne(id).get();
        return new ResponseEntity(habilidad, HttpStatus.OK);
    }

    @PostMapping("/crear")
    public ResponseEntity<?> crear(@RequestBody HabilidadDto habilidadDto) {
        if (StringUtils.isBlank(habilidadDto.getNombreHab())) {
            return new ResponseEntity(new Mensaje("El nombre es requerido"), HttpStatus.BAD_REQUEST);
        }
        if (impHabilidadServicio.existsByNombreHab(habilidadDto.getNombreHab())) {
            return new ResponseEntity(new Mensaje("Esta entrada ya existe"), HttpStatus.BAD_REQUEST);
        }

        Habilidad habilidad = new Habilidad(habilidadDto.getNombreHab(), habilidadDto.getValorHab());
        impHabilidadServicio.save(habilidad);

        return new ResponseEntity(new Mensaje("Habilidad cargada"), HttpStatus.OK);

    }

    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<?> borrar(@PathVariable("id") int id) {
        if (!impHabilidadServicio.existsById(id)) {
            return new ResponseEntity(new Mensaje("El ID no existe"), HttpStatus.BAD_GATEWAY);
        }
        impHabilidadServicio.delete(id);
        return new ResponseEntity(new Mensaje("Entrada eliminada"), HttpStatus.OK);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<?> actualizar(@PathVariable("id") int id, @RequestBody HabilidadDto habilidadDto) {
        if (!impHabilidadServicio.existsById(id)) {
            return new ResponseEntity(new Mensaje("El ID no existe"), HttpStatus.BAD_REQUEST);
        }

        if (impHabilidadServicio.existsByNombreHab(habilidadDto.getNombreHab()) && impHabilidadServicio.getByNombreHab(habilidadDto.getNombreHab()).get().getId() != id) {
            return new ResponseEntity(new Mensaje("Esta habilidad ya está cargada"), HttpStatus.BAD_REQUEST);
        }

        if (StringUtils.isBlank(habilidadDto.getNombreHab())) {
            return new ResponseEntity(new Mensaje("El nombre es requerido"), HttpStatus.BAD_REQUEST);
        }

        Habilidad habilidad = impHabilidadServicio.getOne(id).get();
        habilidad.setNombreHab(habilidadDto.getNombreHab());
        habilidad.setValorHab(habilidadDto.getValorHab());
        impHabilidadServicio.save(habilidad);
        return new ResponseEntity(new Mensaje("Habilidad actualizada"), HttpStatus.OK);
    }
}
