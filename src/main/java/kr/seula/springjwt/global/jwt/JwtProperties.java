package kr.seula.springjwt.global.jwt;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@ConfigurationProperties("jwt")
public class JwtProperties {

    @Value("${jwt.secret}") private String secretKey;

    @Value("${jwt.accessExpired}") private long accessExpired;

    @Value("${jwt.refreshExpired}") private long refreshExpired;

}
