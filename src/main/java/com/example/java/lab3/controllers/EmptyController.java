package com.example.java.lab3.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EmptyController {

    @GetMapping("empty")
    public String empty(Model model) {

        return "empty";
    }

    @GetMapping("q")
    public String q(Model model) {

        return "q";
    }
}
