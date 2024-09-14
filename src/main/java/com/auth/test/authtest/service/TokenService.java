package com.auth.test.authtest.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Service
public class TokenService implements TokenGenerate {
    @Value("${jwt.secretKey}")
    private String secretKey;

    @Override
    public String generateAccessToken(String guid) {
        Algorithm algorithm = Algorithm.HMAC512(secretKey);

        Instant now = Instant.now();
        Instant exp = now.plus(15, ChronoUnit.MINUTES);

        return JWT.create()
                .withSubject(guid)
                .withIssuedAt(Date.from(now))
                .withExpiresAt(Date.from(exp))
                .sign(algorithm);
    }

    @Override
    public String generateRefreshToken(String guid) {
        Algorithm algorithm = Algorithm.HMAC512(secretKey);

        Instant now = Instant.now();
        Instant exp = now.plus(30, ChronoUnit.MINUTES);

        return JWT.create()
                .withSubject(guid)
                .withIssuedAt(Date.from(now))
                .withExpiresAt(Date.from(exp))
                .sign(algorithm);
    }

    @Override
    public void sendEmail() {
        System.out.println("Warning, your IP address has been updated");
    }


}
