package com.example.backend.controller;

import com.example.backend.dto.LoginForm;
import com.example.backend.model.User;
import com.example.backend.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginForm loginForm) {
        User user = loginService.login(loginForm.getLoginId(), loginForm.getPassword());
        if (user == null) {
            return ResponseEntity.ok("fail");
        }
        else
            return ResponseEntity.ok(user);
    }
}
