package com.project.projectN.security;

import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenProvider {

    private final String secretKey = "my-secret-key"; // 환경변수로 바꿔도 됨
    private final long accessTokenValidity = 1000 * 60 * 30;
    private final long refreshTokenValidity = 1000 * 60 * 60 * 24 * 7;

    public String createAccessToken(String email) {
        return createToken(email, accessTokenValidity);
    }

    public String createRefreshToken(String email) {
        return createToken(email, refreshTokenValidity);
    }

    private String createToken(String email, long validity) {
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + validity))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    public String getEmailFromToken(String token) {
        return Jwts.parser().setSigningKey(secretKey)
                .parseClaimsJws(token).getBody().getSubject();
    }
}