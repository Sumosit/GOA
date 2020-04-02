package com.example.java.lab3.repositories;

import com.example.java.lab3.entities.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolesRepository extends JpaRepository<Roles, Long> {
    Roles findByRole(String roleName);
}
