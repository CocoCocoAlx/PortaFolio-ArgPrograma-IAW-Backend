package com.ivanwportfolio.arprog.Repositorio;

import com.ivanwportfolio.arprog.Entidad.Proyecto;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProyectoRepositorio extends JpaRepository<Proyecto, Integer>{
    public Optional<Proyecto> findByNombreProy(String nombreProy);
    public boolean existsByNombreProy(String nombreProy);
}
