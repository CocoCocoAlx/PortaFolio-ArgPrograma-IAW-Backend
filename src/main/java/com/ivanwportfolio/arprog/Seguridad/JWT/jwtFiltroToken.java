package com.ivanwportfolio.arprog.Seguridad.JWT;

import com.ivanwportfolio.arprog.Seguridad.Servicio.ImpUsuarioDetalles;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

public class jwtFiltroToken extends OncePerRequestFilter {

    private final static Logger logger=LoggerFactory.getLogger(jwtFiltroToken.class);

    @Autowired
    jwtProvider jwtProvider;
    @Autowired
    ImpUsuarioDetalles impUsuarioDetalles;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String token = getToken(request);
            if (token != null && jwtProvider.ValidateToken(token)) {
                String nombreUsuario = jwtProvider.getNombreUsuarioDeToken(token);
                UserDetails usuarioDetalles = impUsuarioDetalles.loadUserByUsername(nombreUsuario);
                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(usuarioDetalles, null, usuarioDetalles.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        } catch (Exception e) {
            logger.error("Falló el método doFilterInternal en jwtFiltroToken");
        }
        filterChain.doFilter(request, response);
    }

    private String getToken(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        if (header != null && header.startsWith("Bearer")) {
            return header.replace("Bearer", "");
        }
        return null;
    }
}
