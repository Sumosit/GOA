//package com.example.java.lab3.controllers;
//
//import com.example.java.lab3.entities.Anime;
//import com.example.java.lab3.entities.Comment;
//import com.example.java.lab3.entities.User;
//import com.example.java.lab3.entities.Video;
//import com.example.java.lab3.repositories.*;
//import com.example.java.lab3.service.SecurityService;
//import com.example.java.lab3.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.Authentication;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//
//import java.sql.Timestamp;
//import java.util.Date;
//import java.util.List;
//import java.util.Set;
//
//@RestController
//public class VueJsController {
//
//    @Autowired AnimeRepository animeRepository;
//
//    @Autowired UserRepository userRepository;
//
//    @Autowired UserService userService;
//
//    @GetMapping("home/vue")
//    public List<Anime> Home(Model model, Authentication auth) {
//
//        List<Anime> anime = animeRepository.findAll();
//        if (auth != null) {
//            User user = userService.findByUsername(auth.getName());
//            model.addAttribute("user", user);
//        }
//        model.addAttribute("anime", anime);
//        return anime;
//    }
//}
