package com.example.backend.service;

import com.example.backend.model.User;
import com.example.backend.model.UserRepository;
import org.apache.catalina.security.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User getUserInfo(String userName) {
        return userRepository.findByuserName(userName);
    }

    public User getMyInfo() {
        return userRepository.findById(SecurityUtil.getCurrentUserId());
    }
}
