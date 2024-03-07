package kr.seula.springjwt.global.jwt;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class JwtInfo {

    private String accessToken;

    private String refreshToken;

}
