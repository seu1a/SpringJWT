package kr.seula.springjwt.global.jwt;

import io.jsonwebtoken.Jwts;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JwtUtils {

    private final SecretKey secretKey;

    public JwtUtils(@Value("${jwt.secret}") String jwtSecret) {
        this.secretKey = new SecretKeySpec(jwtSecret.getBytes(StandardCharsets.UTF_8), Jwts.SIG.HS256.key().build().getAlgorithm());
    }

    public String getUsername(String token) {
        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().get("username", String.class);
    }

    public Boolean isExpired(String token) {
        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().getExpiration().before(new Date());
    }

    public JwtInfo generateJwtToken(String username, String role) {
        Date currentTime = new Date(System.currentTimeMillis());
        Date expireTime = new Date(System.currentTimeMillis() + 86400000);

        String accessToken = Jwts.builder()
                .claim("username", username)
                .claim("role", role)
                .issuedAt(currentTime)
                .expiration(expireTime)
                .signWith(secretKey)
                .compact();

        return new JwtInfo(accessToken);
    }

    public String getToken(HttpServletRequest request) {
        String token = request.getHeader("Authoriztion");

        if (StringUtils.hasText(token) && token.startsWith("Bearer ")) {
            return token.substring(7);
        } else {
            return null;
        }
    }

    public Authentication getAuthentication() {

    }

}
