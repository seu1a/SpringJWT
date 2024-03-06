package kr.seula.springjwt.domain.user.controller;

import kr.seula.springjwt.domain.user.dto.LoginDTO;
import kr.seula.springjwt.domain.user.dto.RegisterDTO;
import kr.seula.springjwt.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("login")
    public ResponseEntity<String> login(LoginDTO loginDTO) {
        return ResponseEntity.ok("login successful");
    }

    @PostMapping("register")
    public ResponseEntity<String> register(@RequestBody RegisterDTO registerDTO) {
        userService.register(registerDTO);
        return ResponseEntity.ok("register successful");
    }


}
