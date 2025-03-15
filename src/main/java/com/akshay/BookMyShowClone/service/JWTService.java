package com.akshay.BookMyShowClone.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JWTService {

    private static String secretKey;
    private static final int expirationTime = 24 * 60 * 60 * 1000; // 24 Hours * 60 Minutes * 60 Seconds * 1000 milliseconds

    public JWTService() {
        try {
            secretKey = generateSecretKey();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public String generateJwtToken(String email) {
        Map<String, Object> claims = new HashMap<>();   // To store additional information in JWT Token
        String jwtToken = Jwts.builder()
                .claims(claims)
                .subject(email)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(getKey())
                .compact();

        return jwtToken;
    }

    private String generateSecretKey() throws NoSuchAlgorithmException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("HmacSHA256");
        SecretKey key =  keyGenerator.generateKey();
        return Base64.getEncoder().encodeToString(key.getEncoded());   // Convert Byte Array to String
    }

    private Key getKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);        // Convert String to Byte Array
        return Keys.hmacShaKeyFor(keyBytes);                        // Convert Byte Array to Key object
    }
}
