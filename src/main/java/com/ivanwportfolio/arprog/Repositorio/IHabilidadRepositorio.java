package com.ivanwportfolio.arprog.Repositorio;

import com.ivanwportfolio.arprog.Entidad.Habilidad;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IHabilidadRepositorio extends JpaRepository<Habilidad, Integer>{
    public Optional<Habilidad> findByNombreHab(String nombreHab);
    public boolean existsByNombreHab(String nombreHab);

    
}
