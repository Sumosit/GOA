package com.example.java.lab3.controllers;

import com.example.java.lab3.entities.Roles;
import com.example.java.lab3.entities.User;
import com.example.java.lab3.repositories.RolesRepository;
import com.example.java.lab3.repositories.UserRepository;
import com.example.java.lab3.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.management.relation.Role;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class AdminController {

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RolesRepository rolesRepository;

    @GetMapping("admin")
    public String admin(Model model, Authentication auth) {

        User user = userService.findByUsername(auth.getName());

        List<User> users = userRepository.findByRoles(new Roles((long) 1, "ROLE_USER", null));

        model.addAttribute("user", user);
        model.addAttribute("users", users);

        return "admin";
    }

    @GetMapping("admin/ban/{id}")
    public String admin(@PathVariable Long id) {

        User user = userRepository.getOne(id);
        user.setIsActive("Banned");
        userRepository.save(user);

        return "redirect:/admin#" + id;
    }

    @GetMapping("admin/unban/{id}")
    public String unbanUser(@PathVariable Long id) {
        User user = userRepository.getOne(id);
        user.setIsActive("Active");
        userRepository.save(user);

        return "redirect:/admin#" + id;
    }

    @GetMapping("admin/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        User user = userRepository.getOne(id);
        userRepository.delete(user);

        return "redirect:/admin";
    }

    @GetMapping("/admin/{error}")
    public String registerBadUsername(Model model,
                                      Authentication auth,
                                      @PathVariable String error) {

        User user = userService.findByUsername(auth.getName());
        List<User> users = userRepository.findByRoles(new Roles((long) 1, "ROLE_USER", null));

        model.addAttribute("users", users);
        model.addAttribute("error", error);
        model.addAttribute("user", user);

        return "admin";
    }

    @PostMapping("registerbyadmin")
    public String registerByAdmin(@ModelAttribute("userForm") User userForm, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "admin";
        }
        userForm.setIsActive("Active");

        if (userRepository.findByUsername(userForm.getUsername()) != null &&
                userRepository.findByEmail(userForm.getEmail()) != null) {
            return "redirect:/admin/This email and nickname exists";
        } else {
            if (userRepository.findByUsername(userForm.getUsername()) != null) {
                return "redirect:/admin/This nickname already exists";
            }
            if (userRepository.findByEmail(userForm.getEmail()) != null) {
                return "redirect:/admin/This email already exists";
            }
        }

        if (userForm.getPassword().length() < 6) {
            return "redirect:/admin/Password is too short";
        }

        userService.save(userForm, "ROLE_USER");

        return "redirect:/admin";
    }
}
