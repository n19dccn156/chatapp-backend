package com.project.chatapp.util;

import com.project.chatapp.config.exception.UnauthorizedException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;

public class JwtUtil {
    private static final String JWT_SECRET = "4125442A472D4B6150645367556B58703273357638792F423F4528482B4D6251";
    public static Long ACCESS_TOKEN_EXPIRATION = System.currentTimeMillis() + 1000*60*10;
    public static Long REFRESH_TOKEN_EXPIRATION = System.currentTimeMillis() + 1000*60*60*7;
    private static Key KEY = Keys.hmacShaKeyFor(Decoders.BASE64.decode(JWT_SECRET));
    public static String generateAccessToken(String accountId) {
        return Jwts.builder()
            .setSubject(accountId)
//            .setClaims(claims)
            .setIssuedAt(new Date(System.currentTimeMillis()))
            .setExpiration(new Date(ACCESS_TOKEN_EXPIRATION))
            .signWith(KEY, SignatureAlgorithm.HS256)
            .compact();
    }
    public static String generateRefreshToken(String accountId) {
        return Jwts.builder()
            .setSubject(accountId)
            .setIssuedAt(new Date(System.currentTimeMillis()))
            .setExpiration(new Date(REFRESH_TOKEN_EXPIRATION))
            .signWith(KEY, SignatureAlgorithm.HS256)
            .compact();
    }
    public static String generateAccessTokenAdmin(String email) {
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(ACCESS_TOKEN_EXPIRATION))
                .signWith(KEY, SignatureAlgorithm.HS256)
                .compact();
    }
    public static String generateRefreshTokenAdmin(String email) {
        return Jwts.builder()
            .setSubject(email)
            .setIssuedAt(new Date(System.currentTimeMillis()))
            .setExpiration(new Date(REFRESH_TOKEN_EXPIRATION))
            .signWith(KEY, SignatureAlgorithm.HS256)
            .compact();
    }

    public static String getEmail(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(KEY)
                    .build()
                    .parse(token)
                    .getBody().toString();
        } catch (Exception e) {
            e.printStackTrace();
            throw new UnauthorizedException();
        }
    }
}