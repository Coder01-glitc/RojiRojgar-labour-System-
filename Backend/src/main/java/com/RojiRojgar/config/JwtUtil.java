package com.RojiRojgar.config;

import io.jsonwebtoken.Jwts;

import io.jsonwebtoken.SignatureAlgorithm;

import io.jsonwebtoken.security.Keys;

import java.security.Key;

import java.util.Date;

public class JwtUtil {

    // Secret key

    private static final String SECRET =

            "mysecretkeymysecretkeymysecretkey12";

    // Generate key

    private static final Key key =

            Keys.hmacShaKeyFor(
                    SECRET.getBytes()
            );

    // Generate JWT Token

    public static String generateToken(

            String email,

            String role
    ) {

        return Jwts.builder()

                .setSubject(email)

                .claim("role", role)

                .setIssuedAt(
                        new Date()
                )

                .setExpiration(

                        new Date(

                                System.currentTimeMillis()

                                        + 86400000
                        )
                )

                .signWith(
                        key,
                        SignatureAlgorithm.HS256
                )

                .compact();
    }
}