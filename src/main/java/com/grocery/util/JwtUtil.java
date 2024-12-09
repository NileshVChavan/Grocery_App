package com.grocery.util;



import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import com.grocery.entity.User;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {


    private final String secretKey = "secure-and-long-secret-key-that-meets-security-requirementsfgfdsgdfsgdfgfdsgfs"; 
    private final Key key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
    private final long expirationMs = 86400000; // 24 hours

    public String generateToken(String user) {
        return Jwts.builder()
                .setSubject(user)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationMs))
                .signWith(key, SignatureAlgorithm.HS512) 
                .compact();
    }

 
    public String generateToken(User user) {
        return Jwts.builder()
                .setSubject(user.getEmail()) 
                .claim("role", user.getRole()) 
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationMs)) 
                .signWith(key, SignatureAlgorithm.HS512) 
                .compact();
    }


    public String extractUsername(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key) 
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
    
    
//    public String extractUsername(String token) {
//        Claims claims = Jwts.parserBuilder()
//                            .setSigningKey(key)
//                            .build()
//                            .parseClaimsJws(token)
//                            .getBody();
//        String username = claims.getSubject();
//        String role = claims.get("role", String.class); // Optional, if role is needed
//        
//        // Use the role or any other claim if necessary
//        System.out.println("Role: " + role);
//
//        return username;
//    }


    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(key) 
                    .build()
                    .parseClaimsJws(token); 
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false; 
        }
    }
}
