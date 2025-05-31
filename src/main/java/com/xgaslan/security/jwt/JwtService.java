package com.xgaslan.security.jwt;

import com.xgaslan.data.configs.JwtConfig;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtService {

    private final JwtConfig _jwtConfig;

    public JwtService(JwtConfig jwtConfig) {
        _jwtConfig = jwtConfig;
    }

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> extraClaims = new HashMap<>();

        return Jwts.builder()
                .claims(extraClaims)
                .subject(userDetails.getUsername())
                .issuer(_jwtConfig.getIssuer())
                .audience().add(_jwtConfig.getAudience()).and()
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + _jwtConfig.getExpirationTimeMillis()))
                .signWith(getSigningKey())
                .compact();
    }

    public SecretKey getSigningKey() {
        // Implementation for retrieving the signing key
        var keyBytes = Decoders.BASE64.decode(_jwtConfig.getSecret());
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        // Implementation for extracting claims from the JWT token
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        // Implementation for extracting all claims from the JWT token
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();

    }

    public boolean isTokenExpired(String token) {
        // Implementation for checking if the JWT token is expired
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        // Implementation for extracting expiration date from the JWT token
        return extractClaim(token, Claims::getExpiration);
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        // Implementation for validating the JWT token
        final String username = extractClaim(token, Claims::getSubject);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    public String getUsernameFromToken(String token) {
        // Implementation for extracting username from the JWT token
        return extractClaim(token, Claims::getSubject);
    }

    public String getIssuerFromToken(String token) {
        // Implementation for extracting issuer from the JWT token
        return extractClaim(token, Claims::getIssuer);
    }

}
