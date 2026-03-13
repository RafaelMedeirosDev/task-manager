package com.rafael.task_manager.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.rafael.task_manager.domain.Credential;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.UUID;

@Service
public class TokenService {
    @Value("${JWT_SECRET}")
    private String secret;

    public String generateToken(Credential credential, UUID userId){
      try{
          Algorithm algorithm = Algorithm.HMAC256(secret);

          String token = JWT.create()
                  .withIssuer("auth-api")
                  .withSubject(credential.getEmail())
                  .withExpiresAt(this.generateExpirationDate())
                  .sign(algorithm);
          return token;
      } catch (JWTCreationException err) {
          throw new RuntimeException("Error while generate token", err);
      }
    }

    public String validateToken(String token){
        try{
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("auth-api")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException err) {
            return null;
        }
    }

    private Instant generateExpirationDate(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
