package com.example.backend.controller;

import com.example.backend.dto.LoginForm;
import com.example.backend.model.User;
import com.example.backend.service.LoginService;
import com.example.backend.util.SessionConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/api")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginForm loginForm, HttpSession session) {
        User user = loginService.login(loginForm.getLoginId(), loginForm.getPassword());
        if (user == null) {
            return ResponseEntity.ok("fail");
        }
        session.setAttribute(SessionConstants.LOGIN_USER, user.getUserName());

        return ResponseEntity.ok(user);
    }

    @PostMapping("/logout")
    public ResponseEntity logout(@RequestBody LoginForm loginForm, HttpSession session) {
        if (session != null) {
            session.invalidate();
        }
        return ResponseEntity.ok("ok");
    }
}
