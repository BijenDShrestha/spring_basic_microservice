package com.microservice.gateway.util;

import com.microservice.gateway.exception.JwtTokenMalformedException;
import com.microservice.gateway.exception.JwtTokenMissingException;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String jwtSecret;

    public Claims getClaims(final String token) {
        Claims body = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
        return body;
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
