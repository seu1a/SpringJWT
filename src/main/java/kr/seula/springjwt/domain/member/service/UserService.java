package kr.seula.springjwt.domain.member.service;

import jakarta.transaction.Transactional;
import kr.seula.springjwt.domain.member.dto.LoginDTO;
import kr.seula.springjwt.domain.member.dto.RefreshDTO;
import kr.seula.springjwt.domain.member.dto.RegisterDTO;
import kr.seula.springjwt.domain.member.entity.UserEntity;
import kr.seula.springjwt.domain.member.repository.UserRepository;
import kr.seula.springjwt.global.dto.BaseResponse;
import kr.seula.springjwt.global.jwt.JwtInfo;
import kr.seula.springjwt.global.jwt.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserDetailsService userDetailsService;
    private final JwtUtils jwtUtils;

    public BaseResponse<?> login(LoginDTO loginDTO) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(loginDTO.getUsername());

        if (userDetails.getPassword().equals(loginDTO.getPassword())) {
            return new BaseResponse<>(
                    true,
                    "토큰 발급 성공",
                    jwtUtils.generateJwtToken(
                            loginDTO.getUsername(),
                            userDetails.getAuthorities().iterator().next().getAuthority()
                    )
            );

        } else {
            return new BaseResponse<>(
                    false,
                    "사용자 인증 실패",
                    new ArrayList<>()
            );
        }
    }

    public BaseResponse<?> register(RegisterDTO registerDTO) {
        if (!userRepository.existsByUsername(registerDTO.getUsername())) {
            userRepository.save(
                    UserEntity
                            .builder()
                            .username(registerDTO.getUsername())
                            .role("ROLE_USER")
                            .password(registerDTO.getPassword())
                            .build()
            );

            return new BaseResponse<>(
                    true,
                    "회원가입 성공",
                    new ArrayList<>()
            );
        } else {
            return new BaseResponse<>(
                    false,
                    "회원가입 실패 (이미 존재함)",
                    new ArrayList<>()
            );
        }
    }

    public BaseResponse<?> refresh(RefreshDTO refreshToken) {
        JwtInfo jwtInfo = jwtUtils.refreshToken(refreshToken.getRefreshToken());

        return new BaseResponse<>(
                true,
                "토큰 재발급 성공",
                jwtInfo
        );
    }

}
