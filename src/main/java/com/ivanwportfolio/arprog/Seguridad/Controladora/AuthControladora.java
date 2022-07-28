package com.ivanwportfolio.arprog.Seguridad.Controladora;

import com.ivanwportfolio.arprog.Seguridad.Dto.LoginUsuario;
import com.ivanwportfolio.arprog.Seguridad.Dto.NuevoUsuario;
import com.ivanwportfolio.arprog.Seguridad.Dto.jwtDTO;
import com.ivanwportfolio.arprog.Seguridad.Entidad.Rol;
import com.ivanwportfolio.arprog.Seguridad.Entidad.Usuario;
import com.ivanwportfolio.arprog.Seguridad.JWT.jwtProvider;
import com.ivanwportfolio.arprog.Seguridad.Servicio.RolServicio;
import com.ivanwportfolio.arprog.Seguridad.Servicio.UsuarioServicio;
import com.ivanwportfolio.arprog.Seguridad.enums.RolNombre;
import java.util.HashSet;
import java.util.Set;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthControladora {

    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    UsuarioServicio usuarioServicio;
    @Autowired
    RolServicio rolServicio;
    @Autowired
    jwtProvider jwtProvider;

    @PostMapping("/nuevo")
    public ResponseEntity<?> nuevo(@Valid @RequestBody NuevoUsuario nuevoUsuario, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity(new Mensaje("Campos incorrectos o dirección de email inválida"), HttpStatus.BAD_REQUEST);
        }
        if (usuarioServicio.ExistsByNombreUsuario(nuevoUsuario.getNombreUsuario())) {
            return new ResponseEntity(new Mensaje("Ese nombre de usuario ya existe"), HttpStatus.BAD_REQUEST);
        }
        if (usuarioServicio.ExistsByEmail(nuevoUsuario.getEmail())) {
            return new ResponseEntity(new Mensaje("Esa dirección de email ya existe"), HttpStatus.BAD_REQUEST);
        }

        Usuario usuario = new Usuario(nuevoUsuario.getNombre(), nuevoUsuario.getNombreUsuario(), nuevoUsuario.getEmail(), passwordEncoder.encode(nuevoUsuario.getPassword()));

        Set<Rol> roles = new HashSet<>();
        roles.add(rolServicio.getByRolNombre(RolNombre.ROLE_USER).get());

        if (nuevoUsuario.getRoles().contains("admin")) {
            roles.add(rolServicio.getByRolNombre(RolNombre.ROLE_ADMIN).get());
        }
        usuario.setRoles(roles);
        usuarioServicio.save(usuario);

        return new ResponseEntity(new Mensaje("Usuario guardado"), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<jwtDTO> login(@Valid @RequestBody LoginUsuario loginUsuario, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity(new Mensaje("Campos incorrectos"), HttpStatus.BAD_REQUEST);
        }

        Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUsuario.getNombreUsuario(), loginUsuario.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(auth);

        String jwt = jwtProvider.generateToken(auth);

        UserDetails usuarioDetalles = (UserDetails) auth.getPrincipal();

        jwtDTO jwtDTO = new jwtDTO(jwt, usuarioDetalles.getUsername(), usuarioDetalles.getAuthorities());

        return new ResponseEntity(jwtDTO, HttpStatus.OK);
    }
}