package com.example.BackendFinalProject.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.BackendFinalProject.entity.UserEntity;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

@Service
public class JwtService {
    private final String  secret = "secret";
    private final String  issuer = "issuer";
    public Algorithm algorithm(){
        return Algorithm.HMAC256(secret);
    }

    public String createToken(UserEntity user){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, 60);
        Date expiresAt = calendar.getTime();
        return  JWT.create()
                    .withClaim("principal", user.getUserId())
                    .withClaim("role","user")
                    .withIssuer(issuer)
                    .withExpiresAt(expiresAt)
                    .sign(algorithm());

    }
    public DecodedJWT verifyToken(String token){
       try {
           return  JWT.require(algorithm())
                   .withIssuer(issuer)
                   .build().verify(token);
       }catch (Exception e){
           return null;
       }

    }
}
