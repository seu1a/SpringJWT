package kr.seula.springjwt.domain.user.controller;

import kr.seula.springjwt.domain.user.dto.LoginDTO;
import kr.seula.springjwt.domain.user.dto.RegisterDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("user")
public class UserController {

    @PostMapping("login")
    public ResponseEntity<String> login(LoginDTO loginDTO) {
        return ResponseEntity.ok("login successful");
    }

    @PostMapping("register")
    public ResponseEntity<String> register(RegisterDTO registerDTO) {
        return ResponseEntity.ok("register successful");
    }


}
