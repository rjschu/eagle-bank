package com.eaglebank.api.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Service
public final class JwtService {

    private static final Key jwtSecretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    public static String generateToken(String email) {
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(Date.from(Instant.now().plus(1, ChronoUnit.DAYS)))
                .signWith(jwtSecretKey)
                .compact();
    }

    public static String getUsername(String jwtToken) {
        return Jwts.parserBuilder().setSigningKey(jwtSecretKey)
                .build()
                .parseClaimsJws(jwtToken)
                .getBody()
                .getSubject();
    }
}
