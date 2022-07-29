package com.ivanwportfolio.arprog.Controladora;

import com.ivanwportfolio.arprog.Dto.EstudiosDto;
import com.ivanwportfolio.arprog.Entidad.Estudios;
import com.ivanwportfolio.arprog.Seguridad.Controladora.Mensaje;
import com.ivanwportfolio.arprog.Servicio.ImpEstudiosServicio;
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
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/estudios")
public class EstudioControlador {

    @Autowired
    ImpEstudiosServicio impEstudiosServicio;

    @GetMapping("/lista")
    public ResponseEntity<List<Estudios>> list() {
        List<Estudios> list = impEstudiosServicio.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping("/detalle/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") int id) {
        if(!impEstudiosServicio.existsById(id))
            return new ResponseEntity(new Mensaje ("Estudios inexistentes"),HttpStatus.NOT_FOUND);
        Estudios estudios = impEstudiosServicio.getOne(id).get();
        return new ResponseEntity(estudios, HttpStatus.OK);
    }

    @PostMapping("/crear")
    public ResponseEntity<?> crear(@RequestBody EstudiosDto estudiosDto) {
        if (StringUtils.isBlank(estudiosDto.getNombreEst())) {
            return new ResponseEntity(new Mensaje("El nombre es requerido"), HttpStatus.BAD_REQUEST);
        }
        if (impEstudiosServicio.existsByNombreEst(estudiosDto.getNombreEst())) {
            return new ResponseEntity(new Mensaje("Esta entrada ya existe"), HttpStatus.BAD_REQUEST);
        }

        Estudios estudios = new Estudios(estudiosDto.getDetalleEst(), estudiosDto.getDetalleEst());
        impEstudiosServicio.save(estudios);

        return new ResponseEntity(new Mensaje("Estudios cargados"), HttpStatus.OK);

    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<?> actualizar(@PathVariable("id") int id, @RequestBody EstudiosDto estDto) {
        if (!impEstudiosServicio.existsById(id)) {
            return new ResponseEntity(new Mensaje("El ID no existe"), HttpStatus.BAD_REQUEST);
        }

        if (impEstudiosServicio.existsByNombreEst(estDto.getDetalleEst()) && impEstudiosServicio.getByNombreEst(estDto.getDetalleEst()).get().getId() != id) {
            return new ResponseEntity(new Mensaje("Esta entrada ya está cargada"), HttpStatus.BAD_REQUEST);
        }

        if (StringUtils.isBlank(estDto.getNombreEst())) {
            return new ResponseEntity(new Mensaje("El nombre es requerido"), HttpStatus.BAD_REQUEST);
        }

        Estudios estudios = impEstudiosServicio.getOne(id).get();
        estudios.setNombreEst(estDto.getNombreEst());
        estudios.setDetalleEst(estDto.getDetalleEst());
        impEstudiosServicio.save(estudios);
        return new ResponseEntity(new Mensaje("Estudios actualizados"), HttpStatus.OK);
    }
    
    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<?> borrar(@PathVariable("id") int id) {
        if (!impEstudiosServicio.existsById(id)) {
            return new ResponseEntity(new Mensaje("El ID no existe"), HttpStatus.BAD_REQUEST);
        }
        impEstudiosServicio.delete(id);
        return new ResponseEntity(new Mensaje("Estudios eliminados"), HttpStatus.OK);
    }
}
