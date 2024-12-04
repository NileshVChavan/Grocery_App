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

    // Store your secret key securely in production (e.g., environment variables)
    private final String secretKey = "secure-and-long-secret-key-that-meets-security-requirementsfgfdsgdfsgdfgfdsgfs"; // Replace with a strong key
    private final Key key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8)); // Generate secure Key from secret
    private final long expirationMs = 86400000; // 24 hours

    /**
     * Generate a JWT token for a given username.
     *
     * @param user the username to be included in the token
     * @return the generated JWT token
     */
    public String generateToken(String user) {
        return Jwts.builder()
                .setSubject(user)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationMs))
                .signWith(key, SignatureAlgorithm.HS512) // Sign with secure key
                .compact();
    }

    /**
     * Generate a JWT token for a given User entity with additional claims.
     *
     * @param user the user entity containing username and role
     * @return the generated JWT token
     */
    public String generateToken(User user) {
        return Jwts.builder()
                .setSubject(user.getUsername()) // Set username as the subject
                .claim("role", user.getRole()) // Include the user's role in claims
                .setIssuedAt(new Date()) // Set issued date
                .setExpiration(new Date(System.currentTimeMillis() + expirationMs)) // Set expiration date
                .signWith(key, SignatureAlgorithm.HS512) // Sign with secure key
                .compact();
    }

    /**
     * Extract the username from a JWT token.
     *
     * @param token the JWT token
     * @return the username contained in the token
     */
    public String extractUsername(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key) // Use the same key for parsing
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    /**
     * Validate a JWT token.
     *
     * @param token the JWT token
     * @return true if the token is valid, false otherwise
     */
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(key) // Use the same key for validation
                    .build()
                    .parseClaimsJws(token); // Parse and validate the token
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false; // Token is invalid
        }
    }
}
