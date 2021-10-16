package com.example.backend.dto;

import com.example.backend.model.User;
import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;

@Data
public class LoginFormDto {

    private String loginId;

    private String password;

    private String nickname;

    private String profileUrl;

    private Integer authority;

    public User toUser(PasswordEncoder passwordEncoder){
        return User.builder()
                .userName(loginId)
                .nickName(nickname)
                .password(passwordEncoder.encode(password))
                .profileUrl(null)
                .authority(0)
                .build();
    }
}
