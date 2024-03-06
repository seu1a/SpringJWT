package kr.seula.springjwt.domain.user.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("user")
public class UserController {

    @GetMapping("login")
    public ResponseEntity<String> login() {
        return ResponseEntity.ok("login successful");
    }


}
