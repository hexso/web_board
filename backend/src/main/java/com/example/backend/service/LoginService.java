package com.example.backend.service;

import com.example.backend.dto.LoginFormDto;
import com.example.backend.dto.TokenDto;
import com.example.backend.model.User;
import com.example.backend.model.Repository.UserRepository;
import com.example.backend.util.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final UserRepository userRepository;

    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    private final TokenProvider tokenProvider;

    private final PasswordEncoder passwordEncoder;

    @Transactional
    public TokenDto login(String loginId, String password){
//        User user = userRepository.findByuserName(loginId);
//
//        if(!user.getPassword().equals(password))
//            return null;
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginId,password);
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        TokenDto tokenDto = tokenProvider.generateTokenDto(authentication);

        return tokenDto;
    }

    public User signup(LoginFormDto loginFormDto) {
        if (userRepository.existsByuserName(loginFormDto.getLoginId())) {
            throw new RuntimeException("이미 존재합니다.");
        }

        User user = loginFormDto.toUser(passwordEncoder);
         return userRepository.save(user);
    }

}
