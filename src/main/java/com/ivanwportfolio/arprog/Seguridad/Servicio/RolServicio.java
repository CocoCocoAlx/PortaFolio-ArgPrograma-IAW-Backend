package com.ivanwportfolio.arprog.Seguridad.Servicio;

import com.ivanwportfolio.arprog.Seguridad.Entidad.Rol;
import com.ivanwportfolio.arprog.Seguridad.Repositorio.iRolRepositorio;
import com.ivanwportfolio.arprog.Seguridad.enums.RolNombre;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class RolServicio {
    @Autowired
    iRolRepositorio iRolRepo;
    public Optional<Rol> getByRolNombre(RolNombre rolNombre){
        return iRolRepo.findByRolNombre(rolNombre);
    }
    public void save(Rol rol){
        iRolRepo.save(rol);
    }
}
