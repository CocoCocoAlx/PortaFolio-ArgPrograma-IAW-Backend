package com.ivanwportfolio.arprog.Repositorio;

import com.ivanwportfolio.arprog.Entidad.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPersonaRepositorio extends JpaRepository<Persona,Integer> {
    
}
