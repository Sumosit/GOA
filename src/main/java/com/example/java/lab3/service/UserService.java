package com.example.java.lab3.service;

import com.example.java.lab3.entities.User;

public interface UserService {
    void save(User user, String role);

    User findByUsername(String username);
}
