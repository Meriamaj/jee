package com.example;

import java.security.Key;
import java.util.Date;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

public class JWTUtils {
   
    private static final String keySec = "exempledeeclesecretdevalidation" ;
    private static final long tempsExpir = 800000000;

    private static final Key cle = Keys.hmacShaKeyFor(keySec.getBytes());
    public static String generertoken(String id) {
        return Jwts.builder().setSubject(id).setIssuedAt(new Date()).setExpiration(new Date(System.currentTimeMillis()+tempsExpir)).signWith(cle,SignatureAlgorithm.HS256).compact();

    }

    public static Claims validerToken(String token) throws Exception {
        return Jwts.parserBuilder().setSigningKey(cle).build().parseClaimsJws(token).getBody();

    }
}
