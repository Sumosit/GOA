package com.example.java.lab3.controllers;

import com.example.java.lab3.entities.Anime;
import com.example.java.lab3.entities.Bookmark;
import com.example.java.lab3.entities.Roles;
import com.example.java.lab3.entities.User;
import com.example.java.lab3.repositories.AnimeRepository;
import com.example.java.lab3.repositories.BookmarkRepository;
import com.example.java.lab3.repositories.UserRepository;
import com.example.java.lab3.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@Controller
public class ProfileController {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    AnimeRepository animeRepository;

    @Autowired
    BookmarkRepository bookmarkRepository;

    @GetMapping("profile")
    public String profile(Model model, Authentication auth) {

        User user = userService.findByUsername(auth.getName());
        Set<Bookmark> bk = bookmarkRepository.findAllByUser(user);

        model.addAttribute("bk", bk);
        model.addAttribute("user", user);
        return "profile";
    }

    @GetMapping("profile/{error}")
    public String errorProfile(Model model, Authentication auth,
                               @PathVariable String error) {

        User user = userService.findByUsername(auth.getName());

        model.addAttribute("error", error);
        model.addAttribute("user", user);
        return "profile";
    }

    @PostMapping("saveemail/{id}")
    public String saveEmail(@PathVariable Long id,
                            Authentication auth,
                            @ModelAttribute("userForm") User userForm,
                            @RequestParam String email) {


        if (userRepository.findByEmail(userForm.getEmail()) != null) {
            return "redirect:/profile/This email already exists";
        }
        else {
            User user = userRepository.findByUsername(auth.getName());
            user.setEmail(email);
            userRepository.save(user);
        }
        return "redirect:/profile";
    }
    @PostMapping("saveusername/{id}")
    public String saveUsername(@PathVariable Long id,
                            Authentication auth,
                            @ModelAttribute("userForm") User userForm,
                            @RequestParam String username) {

        if (userRepository.findByUsername(userForm.getUsername()) != null) {
            return "redirect:/profile/This Username already exists";
        }
        else {
            User user = userRepository.findByUsername(auth.getName());
            user.setUsername(username);
            userRepository.save(user);
        }
        return "redirect:/logout";
    }

    @PostMapping("savepassowrd/{id}")
    public String savePassword(@PathVariable Long id,
                               Authentication auth,
                               @ModelAttribute("userForm") User userForm,
                               @RequestParam String newpassword,
                               @RequestParam String renewpassword) {

//        if (userRepository.findByUsername(userForm.getUsername()) != null &&
//                userRepository.findByEmail(userForm.getEmail()) != null) {
//            return "redirect:/profile/This email and nickname exists";
//        } else {
//            if (userRepository.findByUsername(userForm.getUsername()) != null) {
//                return "redirect:/profile/This nickname already exists";
//            }
//            if (userRepository.findByEmail(userForm.getEmail()) != null) {
//                return "redirect:/profile/This email already exists";
//            }
//        }
        if (newpassword.length() < 6 || renewpassword.length() < 6) {
            return "redirect:/profile/Password is too short";
        }
        if (!newpassword.equals(renewpassword)) {
            return "redirect:/profile/Password error";
        }
        if (bCryptPasswordEncoder.matches(userForm.getPassword(), userService.findByUsername(auth.getName()).getPassword())) {
            System.out.println("aisdioa");
            User user = userRepository.getOne(id);
            user.setPassword(newpassword);
        } else {
            return "redirect:/profile/Password error";
        }

        return "redirect:/login";
    }

}
