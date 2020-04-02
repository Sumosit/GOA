package com.example.java.lab3.entities;

import com.example.java.lab3.entities.Roles;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String password;

    @Transient
    private String passwordConfirm;

    private String username;

    private String email;

    private String isActive;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Roles> roles;

    @OneToMany(fetch = FetchType.EAGER)
    private Set<User> friends;

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

}
