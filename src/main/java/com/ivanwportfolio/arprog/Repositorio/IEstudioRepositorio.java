package com.ivanwportfolio.arprog.Repositorio;

import com.ivanwportfolio.arprog.Entidad.Estudios;
import com.ivanwportfolio.arprog.Entidad.Experiencia;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IEstudioRepositorio extends JpaRepository<Estudios, Integer> {
    public Optional<Estudios> findByNombreEst(String nombreEst);
    public boolean existsByNombreEst(String nombreEst);
}
