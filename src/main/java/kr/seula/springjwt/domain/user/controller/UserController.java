package kr.seula.springjwt.domain.user.controller;

import kr.seula.springjwt.domain.user.dto.LoginDTO;
import kr.seula.springjwt.domain.user.dto.RegisterDTO;
import kr.seula.springjwt.domain.user.service.UserService;
import kr.seula.springjwt.global.dto.BaseResponse;
import kr.seula.springjwt.global.jwt.JwtInfo;
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

}
