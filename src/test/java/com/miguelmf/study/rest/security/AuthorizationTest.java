package com.miguelmf.study.rest.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.InvalidClaimException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestReporter;

import java.io.UnsupportedEncodingException;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AuthorizationTest {



    @Test
    @DisplayName("Cria token JWT")
    void criaToken(TestReporter reporter) throws UnsupportedEncodingException {
        Algorithm algorithm = Algorithm.HMAC256("minha-senha-secreta");

        String token = JWT.create()
                .withIssuer("auth0")
                .sign(algorithm);

        reporter.publishEntry("token criado", token);
    }

    @Test
    @DisplayName("Verifica se token é válido")
    void verificaToken(TestReporter reporter) throws UnsupportedEncodingException {
        Algorithm algorithm = Algorithm.HMAC256("minha-senha-secreta");
        String token = JWT.create()
                .withIssuer("auth0")
                .sign(algorithm);

        JWTVerifier verifier = JWT.require(algorithm)
                .withIssuer("auth0")
                .build();

        DecodedJWT jwt = verifier.verify(token);

        Map<String, String> valores = new HashMap<>();
        valores.put("Header", jwt.getHeader());
        valores.put("Payload", jwt.getPayload());
        valores.put("Signature", jwt.getSignature());
        valores.put("Token", jwt.getToken());
        valores.put("Token", jwt.getClaims().toString());
        jwt.getClaims().forEach((key, value) -> valores.put(key, value.asString()));

        reporter.publishEntry(valores);
    }

    @Test
    @DisplayName("Token deve possuir claim ou erro é lançado")
    void verificaSeTokenPossuiClaim(TestReporter reporter) throws UnsupportedEncodingException {
        Algorithm algorithm = Algorithm.HMAC256("minha-senha-secreta");
        String token = JWT.create()
                .withIssuer("auth0")
                .sign(algorithm);

        JWTVerifier verifier = JWT.require(algorithm)
                .withIssuer("auth0")
                .withClaim("name", "miguel")
                .build();

        Throwable exception = assertThrows(InvalidClaimException.class, () -> verifier.verify(token));

        reporter.publishEntry("Exception quando sem claim", exception.getMessage());
        assertTrue(exception.getMessage().startsWith("The Claim"));
    }

    @Test
    @DisplayName("Decodifica um Tokwn JWT")
    void decodificaJwt(TestReporter reporter) throws UnsupportedEncodingException {
        Algorithm algorithm = Algorithm.HMAC256("minha-senha-secreta");
        String token = JWT.create()
                .withIssuer("auth0")
                .withClaim("name", "miguel")
                .withExpiresAt(Date.from(Instant.now()))
                .sign(algorithm);

        JWTVerifier verifier = JWT.require(algorithm)
                .withIssuer("auth0")
                .withClaim("name", "miguel")
                .acceptExpiresAt(5) // expira em 5
                .build();

        DecodedJWT jwt = JWT.decode(token); // DECODIFICA

        Map<String, String> valores = new HashMap<>();
        valores.put("Token", jwt.getToken());
        jwt.getClaims().forEach((key, value) -> valores.put(key, value.asString()));

        valores.remove("exp"); // Removo porque este valor é NULL é o TestReporter não gosta disso
        reporter.publishEntry(valores);
    }


}
