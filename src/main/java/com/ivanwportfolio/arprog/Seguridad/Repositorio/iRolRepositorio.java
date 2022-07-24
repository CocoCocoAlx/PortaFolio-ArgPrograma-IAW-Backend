package com.ivanwportfolio.arprog.Seguridad.Repositorio;

import com.ivanwportfolio.arprog.Seguridad.Entidad.Rol;
import com.ivanwportfolio.arprog.Seguridad.enums.RolNombre;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface iRolRepositorio extends JpaRepository<Rol, Integer>{
    Optional<Rol> findByRolNombre(RolNombre rolNombre);
}
