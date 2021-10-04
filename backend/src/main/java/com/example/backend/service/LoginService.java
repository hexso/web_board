package com.example.backend.service;

import com.example.backend.model.User;
import com.example.backend.model.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    @Autowired
    private UserRepository userRepository;

    public User login(String loginId, String password){
        User user = userRepository.findByuserName(loginId);

        if(user.getPassword().equals(password))
            return user;
        else
            return null;
    }

}
