package com.Uninter.VidaPlus.Service;

import com.Uninter.VidaPlus.Entity.UserSystemEntity;
import com.Uninter.VidaPlus.Exceptions.InvalidTokenException;
import com.Uninter.VidaPlus.Exceptions.TokenGenerationException;
import com.Uninter.VidaPlus.Repository.UserSystemRepository;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.stereotype.Service;


import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    private final Integer accessTokenExpiration = 30;

    private final Integer refreshTokenExpiration = 120;

    public String generateToken(UserSystemEntity userEntity) {

        try {
            Algorithm algorithm = Algorithm.HMAC256("15479");
            return JWT.create()
                    .withIssuer("VidaPlus")
                    .withSubject(userEntity.getLogin())
                    .withExpiresAt(expirationDate(accessTokenExpiration))
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            throw new TokenGenerationException("TOKEN_GENERATION_ERROR", "Erro ao gerar token JWT de refresh!");
        }

    }

    public String generateRefreshToken(UserSystemEntity userEntity) {

        try {
            Algorithm algorithm = Algorithm.HMAC256("15479");
            return JWT.create()
                    .withIssuer("VidaPlus")
                    .withSubject(userEntity.getLogin())
                    .withExpiresAt(expirationDate(refreshTokenExpiration))
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            throw new TokenGenerationException("TOKEN_GENERATION_ERROR", "Erro ao gerar token JWT de refresh!");
        }

    }

    public String verifyToken(String token) {
        DecodedJWT decodedJWT;
        try {
            Algorithm algorithm = Algorithm.HMAC256("15479");
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("VidaPlus")
                    .build();

            decodedJWT = verifier.verify(token);
            return decodedJWT.getSubject();
        } catch (JWTVerificationException exception){
            throw new InvalidTokenException("INVALID_TOKEN", "Token invalido!");
        }
    }



    private Instant expirationDate(Integer i) {
        return LocalDateTime.now().plusMinutes(i).toInstant(ZoneOffset.of("-03:00"));
    }
}
