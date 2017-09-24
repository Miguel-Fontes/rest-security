package com.miguelmf.study.rest.security.auth;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.InvalidClaimException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.io.UnsupportedEncodingException;

public class Autorizador {

    private final String password = "Ad65a4da3d2Asd8Awd84aw8dq!@#!@$!53655414481944As6d4asd1341";

    public Autorizador() {

    }

    public Token geraToken(String user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(password);

            String token = JWT.create()
                    .withIssuer("BotecoAppTokenServer")
                    .withClaim("app", "boteco")
                    .withClaim("user", user)
                    .sign(algorithm);

            return new Token(token);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    public Boolean verifica(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256("minha-senha-secreta");

            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("BotecoAppTokenServer")
                    .withClaim("app", "boteco")
                    .withClaim("user", "")
                    .acceptExpiresAt(5) // expira em 5
                    .build();

            DecodedJWT jwt = verifier.verify(token);

            return true;
        } catch (UnsupportedEncodingException | InvalidClaimException e) {
            e.printStackTrace();
            return false;
        }

    }

}
