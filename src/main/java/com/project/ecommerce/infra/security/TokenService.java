package com.project.ecommerce.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.project.ecommerce.exception.GenerationTokenException;
import com.project.ecommerce.models.customer.Customer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class TokenService {

    @Value("${token.secret}")
    private String secret;

    @Value("${token.expiration.time}")
    private Integer expirationTimeInSeconds;

    public String generateToken(Customer customer) {
       try {
           Algorithm algorithm = Algorithm.HMAC256(secret);
           return JWT.create()
                   .withIssuer("ecommerce")
                   .withSubject(customer.getEmail().value())
                   .withExpiresAt(getExpirationTime())
                   .sign(algorithm);
       } catch (JWTCreationException exception) {
            throw new GenerationTokenException("Error while generating token");
       }
    }

    public String validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("ecommerce")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException e) {
            return "";
        }

    }

    private Instant getExpirationTime() {
        return Instant.now().plusSeconds(expirationTimeInSeconds);
    }

    public Integer getExpirationTimeInSeconds() {
        return this.expirationTimeInSeconds;
    }
}
