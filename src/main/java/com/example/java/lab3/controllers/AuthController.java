package com.example.java.lab3.controllers;

import com.example.java.lab3.entities.Roles;
import com.example.java.lab3.entities.User;
import com.example.java.lab3.repositories.UserRepository;
import com.example.java.lab3.service.SecurityService;
import com.example.java.lab3.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@Controller
public class AuthController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @GetMapping("register")
    public String Register(Model model,
                           Authentication auth) {
        return "register";
    }

    @GetMapping("/logout")
    public String Logout() {
        return "redirect:/login";
    }

    @GetMapping("/register/{bad}")
    public String registerBadUsername(Model model,
                                      Authentication auth,
                                      @PathVariable String bad) {

        model.addAttribute("bad", bad);

        return "register";
    }

    @PostMapping("register")
    public String Register_(@ModelAttribute("userForm") User userForm, BindingResult bindingResult) {

                if (bindingResult.hasErrors()) {
                    return "register";
                }
        userForm.setIsActive("Active");

        User user = userService.findByUsername(userForm.getUsername());

        if (userRepository.findByUsername(userForm.getUsername()) != null &&
                userRepository.findByEmail(userForm.getEmail()) != null) {
            return "redirect:/register/This email and nickname exists";
        } else {
            if (userRepository.findByUsername(userForm.getUsername()) != null) {
                return "redirect:/register/This nickname already exists";
            }
            if (userRepository.findByEmail(userForm.getEmail()) != null) {
                return "redirect:/register/This email already exists";
            }
        }

        if (userForm.getPassword().length() < 6 && !userForm.getPassword().equals(userForm.getPasswordConfirm())) {
            return "redirect:/register/Password is too short<br>Passwords do not match";
        } else {
            if (userForm.getPassword().length() < 6) {
                return "redirect:/register/Password is too short";
            }

            if (!userForm.getPassword().equals(userForm.getPasswordConfirm())) {
                return "redirect:/register/Passwords do not match";
            }
        }

        userService.save(userForm, "ROLE_USER");

        securityService.autoLogin(userForm.getUsername(), userForm.getPasswordConfirm());

        return "redirect:/login";
    }

    @GetMapping("login")
    public String login(Model model, String error, String logout, Authentication auth) {
        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");
        return "login";
    }
}
