package kr.seula.springjwt.domain.member.controller;

import kr.seula.springjwt.domain.member.dto.LoginDTO;
import kr.seula.springjwt.domain.member.dto.RefreshDTO;
import kr.seula.springjwt.domain.member.dto.RegisterDTO;
import kr.seula.springjwt.domain.member.service.UserService;
import kr.seula.springjwt.global.dto.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("login")
    public ResponseEntity<BaseResponse<?>> login(@RequestBody LoginDTO loginDTO) {
        return ResponseEntity.ok(userService.login(loginDTO));
    }

    @PostMapping("register")
    public ResponseEntity<BaseResponse<?>> register(@RequestBody RegisterDTO registerDTO) {
        return ResponseEntity.ok(userService.register(registerDTO));
    }

    @PostMapping("refresh")
    public ResponseEntity<BaseResponse<?>> refresh(@RequestBody RefreshDTO refreshToken) {
        return ResponseEntity.ok(userService.refresh(refreshToken));
    }

}
