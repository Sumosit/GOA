package com.example.java.lab3.controllers;

import com.example.java.lab3.entities.User;
import com.example.java.lab3.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MangaController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("manga")
    public String manga(Model model,
                        Authentication auth) {

        User user = userRepository.findByUsername(auth.getName());

        model.addAttribute("user", user);

        return "manga";
    }

}
