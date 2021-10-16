package com.example.backend.model;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByuserName(String loginId);
    boolean existsByuserName(String loginId);
}

