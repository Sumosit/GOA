package com.example.java.lab3.service;

public interface SecurityService {
    String findLoggedInUsername();

    void autoLogin(String username, String password);
}
