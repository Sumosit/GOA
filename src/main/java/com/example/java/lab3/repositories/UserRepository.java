package com.example.java.lab3.repositories;

import com.example.java.lab3.entities.Roles;
import com.example.java.lab3.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    User findByEmail(String email);
    List<User> findByRoles(Roles role);
}
