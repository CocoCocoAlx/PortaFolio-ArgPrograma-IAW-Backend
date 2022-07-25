package com.ivanwportfolio.arprog.Controladora;

import com.ivanwportfolio.arprog.Entidad.Persona;
import com.ivanwportfolio.arprog.Interface.IPersonaServicio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
/* Añadido por si surgen problemas más adelante al hacer las peticiones desde el frontend */
 /* @CrossOrigin(origins = "http://localhost:4200") */
public class PersonaControladora {

    @Autowired
    IPersonaServicio ipersonaservicio;

    @GetMapping("personas/lista")
    public List<Persona> getPersona() {
        return ipersonaservicio.getPersona();
    }

    @GetMapping("personas/ver")
    public Persona buscarPersona() {
        return ipersonaservicio.buscarPersona((int) 1);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("personas/crear")
    public String crearPersona(@RequestBody Persona persona) {
        ipersonaservicio.guardarPersona(persona);
        return "Datos cargados correctamente";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("personas/borrar/{id}")
    public String eliminarPersona(@PathVariable int id) {
        ipersonaservicio.eliminarPersona(id);
        return "Persona eliminada";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("personas/editar/{id}")
    public String modificarPersona(@PathVariable int id, @RequestParam("nombre") String nombreNuevo, @RequestParam("apellido") String apellidoNuevo, @RequestParam("imagen") String imagenNueva) {
        Persona persona = ipersonaservicio.buscarPersona(id);
        persona.setNombre(nombreNuevo);
        persona.setApellido(apellidoNuevo);
        persona.setImagen(imagenNueva);
        ipersonaservicio.guardarPersona(persona);
        return "Datos actualizados correctamente";
    }
}
