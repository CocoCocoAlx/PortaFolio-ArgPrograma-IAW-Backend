package com.ivanwportfolio.arprog.Seguridad.Servicio;

import com.ivanwportfolio.arprog.Seguridad.Entidad.Admin;
import com.ivanwportfolio.arprog.Seguridad.Entidad.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ImpUsuarioDetalles implements UserDetailsService{
    @Autowired
    UsuarioServicio usuarioServicio;

    @Override
    public UserDetails loadUserByUsername(String nombreUsuario) throws UsernameNotFoundException {
        Usuario usuario = usuarioServicio.getByUsuarioNombre(nombreUsuario).get();
        return Admin.build(usuario);
    }
    
    
}
