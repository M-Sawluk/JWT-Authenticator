package com.tecza.jwt_generator.jwt;

import com.tecza.jwt_generator.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.sql.Date;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class JwtUtils {
    private static final String SECRET_PASSWORD = "TECZA123";
    private JwtUtils() {}

    public static String createToken(User user, String userAgent) {
        int expirationLengthInMinutes = JwtExpirationTimePolicy.getExpirationLengthInMinutes(userAgent);

        LocalDateTime dateTime = LocalDateTime
                .now()
                .plusMinutes(expirationLengthInMinutes);

        Instant instant = dateTime
                .atZone(ZoneId.systemDefault())
                .toInstant();

        Claims claims = Jwts
                .claims()
                .setSubject(user.getEmail());
        claims.put("userId", String.valueOf(user.getId()));
        claims.put("role", user.getRole().getRoleName());

        return Jwts
                .builder()
                .setClaims(claims)
                .setExpiration(Date.from(instant))
                .signWith(SignatureAlgorithm.HS512, SECRET_PASSWORD)
                .compact();
    }
}
