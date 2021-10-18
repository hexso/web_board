package com.example.backend.model.Repository;

import com.example.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByuserName(String loginId);
    boolean existsByuserName(String loginId);
}

