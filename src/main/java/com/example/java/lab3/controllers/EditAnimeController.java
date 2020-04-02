package com.example.java.lab3.controllers;

import com.example.java.lab3.entities.Anime;
import com.example.java.lab3.entities.User;
import com.example.java.lab3.repositories.AnimeRepository;
import com.example.java.lab3.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Timestamp;
import java.util.Date;

@Controller
public class EditAnimeController {

    @Autowired
    UserService userService;

    @Autowired
    AnimeRepository animeRepository;

    @GetMapping(value = "/addanime")
    public String addAnime(Model model) {

        return "addAnime";
    }

    @PostMapping(value = "/addanime")
    public String addAnime(@RequestParam String address,
                           @RequestParam String date,
                           @RequestParam String genre,
                           @RequestParam String history,
                           @RequestParam String image,
                           @RequestParam String links,
                           @RequestParam String name,
                           @RequestParam String producer,
                           Authentication auth) {
        User author = userService.findByUsername(auth.getName());
        Date d= new Date();
        long time = d.getTime();
        Anime anime = new Anime(null, 0, 0, history, links, name, image, genre, date, producer, address, new Timestamp(time), null, author);
        animeRepository.save(anime);
        return "redirect:/home";
    }
}
