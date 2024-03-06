package kr.seula.springjwt.global.jwt;

import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Date;

public class JwtUtils {

    private final SecretKey secretKey;

    public JwtUtils(@Value("${jwt.secret}") String jwtSecret) {
        this.secretKey = new SecretKeySpec(jwtSecret.getBytes(StandardCharsets.UTF_8), Jwts.SIG.HS256.key().build().getAlgorithm());
    }

    public String getEmail(String accessToken) {
        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(accessToken).getPayload().get("email", String.class);
    }

    public JwtInfo generateJwtToken(String email) {
        Date currentTime = new Date(System.currentTimeMillis());
        Date expireTime = new Date(System.currentTimeMillis() + 86400000);

        String accessToken = Jwts.builder()
                .claim("email", email)
                .issuedAt(currentTime)
                .expiration(expireTime)
                .signWith(secretKey)
                .compact();

        return new JwtInfo(accessToken);
    }

}
