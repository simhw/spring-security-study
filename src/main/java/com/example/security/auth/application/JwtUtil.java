package com.example.security.auth.application;

import com.example.security.auth.domain.UserDetailsImpl;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;

import java.security.Key;
import java.util.Base64;
import java.util.Date;

@Slf4j
public class JwtUtil {

    private static final String SECRET_KEY = "Zbfxkzg1pccSr01uN1Xq37fq10zQZDuqKMLzt3LJXLM=";
    private static final int EXPIRATION = 60 * 60 * 1000; // 1 hour
    private static final Key HMAC_KEY = Keys.hmacShaKeyFor(Base64.getDecoder().decode(SECRET_KEY));

    public static String generateToken(UserDetailsImpl userDetails) {
        return Jwts.builder()
                .setSubject(String.valueOf(userDetails.getId()))
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
                .signWith(HMAC_KEY, SignatureAlgorithm.HS256).compact();
    }

    public static Claims extractClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(HMAC_KEY)
                .build().parseClaimsJws(token)
                .getBody();
    }

    public static boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(HMAC_KEY).build().parseClaimsJws(token);
            return true;

        } catch (MalformedJwtException e) {
            log.info("Invalid JWT Token", e);
        } catch (ExpiredJwtException e) {
            log.info("Expired JWT Token", e);
        } catch (UnsupportedJwtException e) {
            log.info("Unsupported JWT Token", e);
        } catch (IllegalArgumentException e) {
            log.info("JWT claims string is empty.", e);
        }

        return false;
    }
}