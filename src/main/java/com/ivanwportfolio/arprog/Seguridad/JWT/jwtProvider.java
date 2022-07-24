package com.ivanwportfolio.arprog.Seguridad.JWT;

import com.ivanwportfolio.arprog.Seguridad.Entidad.Admin;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class jwtProvider {

    private final static Logger logger = LoggerFactory.getLogger(jwtProvider.class);

    @Value("$(jwt.secret")
    private String secreto;
    @Value("$(jwt.expiration")
    private int expiration;

    public String generateToken(Authentication auth) {
        Admin admin = (Admin) auth.getPrincipal();
        return Jwts.builder().setSubject(admin.getUsername()).setIssuedAt(new Date()).setExpiration(new Date(new Date().getTime() + expiration * 3600)).signWith(SignatureAlgorithm.HS512, secreto).compact();
    }

    public String getNombreUsuarioDeToken(String token) {
        return Jwts.parser().setSigningKey(secreto).parseClaimsJws(token).getBody().getSubject();
    }

    public boolean ValidateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secreto).parseClaimsJws(token);
            return true;
        } catch (MalformedJwtException e) {
            logger.error("El token está mal formado");
        } catch (ExpiredJwtException e) {
            logger.error("El token ha expirado");
        } catch (IllegalArgumentException e) {
            logger.error("El token está vacío");
        } catch (SignatureException e) {
            logger.error("Error en la firma del token");
        } catch (UnsupportedJwtException e) {
            logger.error("Token no soportado");
        }
        return false;
    }
}