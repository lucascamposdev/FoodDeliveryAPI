package presto.com.FoodDeliveryAPI.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import presto.com.FoodDeliveryAPI.dtos.token.TokenValidationResult;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    public String generateToken(Authenticable authenticable){
        try{
            Algorithm algorithm = Algorithm.HMAC256(secret);

            return JWT.create()
                    .withIssuer("foodDelivery-api")
                    .withSubject(authenticable.getEmail())
                    .withExpiresAt(generateExpirationDate())
                    .withClaim("accountType", authenticable.getAccountType().toString())
                    .sign(algorithm);
        }catch (JWTCreationException ex){
            throw new RuntimeException("Erro ao gerar token", ex);
        }
    }

    public TokenValidationResult validateToken(String token){
        try{
            Algorithm algorithm = Algorithm.HMAC256(secret);

            DecodedJWT jwt = JWT.require(algorithm)
                    .withIssuer("foodDelivery-api")
                    .build()
                    .verify(token);

            String subject = jwt.getSubject();

            String claims = jwt.getClaim("accountType").asString();
            System.out.println(claims);
            return new TokenValidationResult(subject, claims);

        }catch (JWTVerificationException ex){
            return null;
        }
    }

    private Instant generateExpirationDate (){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
