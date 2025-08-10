package com.example.auth_service.service;

import com.example.auth_service.dto.UserJwt;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class JwtService {
    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.expiration}")
    private long expiration;

    public String generateAccessToken(Map<String, Object> userDetails, String username) {
        return buildToken(Map.of(), username, expiration);
    }

    private String buildToken(Map<String, Object> claims, String userDetails, long expiration) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSignInKey(secretKey), SignatureAlgorithm.HS256)
                .compact();
    }

    private @NotNull Key getSignInKey(String secretKey) {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public UserJwt parseToken(String token) {
        try {
            var claims = Jwts.parserBuilder()
                    .setSigningKey(getSignInKey(secretKey))
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            UserJwt userJwt = new UserJwt();
            userJwt.setUsername(claims.get("username", String.class));
            userJwt.setEmail(claims.get("email", String.class));
            userJwt.setKey(UUID.fromString(claims.get("key", String.class)));
            return userJwt;
        } catch (Exception e) {
            return null;
        }
    }

    public UserJwt parseTokenAllowExpired(String token) {
        try {
            return parseToken(token);
        } catch (ExpiredJwtException e) {
            var claims = e.getClaims();

            UserJwt userJwt = new UserJwt();
            userJwt.setUsername(claims.get("username", String.class));
            userJwt.setEmail(claims.get("email", String.class));
            userJwt.setKey(UUID.fromString(claims.get("key", String.class)));
            return userJwt;
        } catch (Exception e) {
            return null;
        }
    }
}
