package com.microservice.auth.util;

import com.microservice.auth.exception.JwtTokenMalformedException;
import com.microservice.auth.exception.JwtTokenMissingException;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.token.validity}")
    private Long tokenValidity;

    public Claims getClaim(final String token) {
        Claims body = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
        return body;
    }

    public String generateToken(String id) {
        Claims claims = Jwts.claims().setId(id);
//        to add roles
//        claims.put("roles", values)

        Long nowMillis = System.currentTimeMillis();
        Long expMillis = nowMillis + tokenValidity;
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(nowMillis))
                .setExpiration(new Date(expMillis))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();

    }

    public void validateToken(final String token) throws JwtTokenMalformedException, JwtTokenMissingException {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);

        } catch (SignatureException e) {
            throw new JwtTokenMalformedException("Invalid jwt signature");
        } catch (MalformedJwtException e) {
            throw new JwtTokenMalformedException("Invalid jwt token");
        } catch (ExpiredJwtException e) {
            throw new JwtTokenMalformedException("Expired jwt token");
        } catch (UnsupportedJwtException e) {
            throw new JwtTokenMalformedException("Unsupported jwt token");
        } catch (IllegalArgumentException e) {
            throw new JwtTokenMissingException("jwt claims string is empty");
        }

    }


}
