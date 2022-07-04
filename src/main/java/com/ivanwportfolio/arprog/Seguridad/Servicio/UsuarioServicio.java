package com.ivanwportfolio.arprog.Seguridad.Servicio;

import com.ivanwportfolio.arprog.Seguridad.Entidad.Usuario;
import com.ivanwportfolio.arprog.Seguridad.Repositorio.iUsuarioRepositorio;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UsuarioServicio {
    @Autowired
    iUsuarioRepositorio iUsuarioRepo;
        public Optional<Usuario> getByUsuarioNombre(String nombreUsuario){
            return iUsuarioRepo.findByNombreUsuario(nombreUsuario);
        }
        
        public boolean ExistsByNombreUsuario(String nombreUsuario){
            return iUsuarioRepo.existsByNombreUsuario(nombreUsuario);
        }

        public boolean ExistsByCorreo(String email){
            return iUsuarioRepo.existsByCorreo(email);
        }
        
        public void save(Usuario usuario){
            iUsuarioRepo.save(usuario);
        }
}
