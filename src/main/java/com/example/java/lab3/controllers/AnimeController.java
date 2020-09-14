package com.example.java.lab3.controllers;

import com.example.java.lab3.entities.*;
import com.example.java.lab3.repositories.*;
import com.example.java.lab3.service.SecurityService;
import com.example.java.lab3.service.UserService;
import org.hibernate.mapping.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.sql.Timestamp;
import java.util.*;

@Controller
public class AnimeController {

    @Autowired
    AnimeRepository animeRepository;

    @Autowired
    VideoRepository videoRepository;

    @Autowired
    SecurityService securityService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @Autowired
    BookmarkRepository bookmarkRepository;

    @Autowired
    CommentRepository commentRepository;

    @GetMapping("home")
    public String Home(Model model, Authentication auth) {

        List<Anime> anime = animeRepository.findAll();
        if (auth != null) {
            User user = userService.findByUsername(auth.getName());
            model.addAttribute("user", user);
        }
        model.addAttribute("anime", anime);
        return "home";
    }

    @GetMapping("animesearch")
    public String searchAnime(Model model,
                              Authentication auth,
                              @RequestParam String name) {

        User user = userRepository.findByUsername(auth.getName());
        List<Anime> anime = animeRepository.findByNameContainingIgnoreCase(name);

        model.addAttribute("user", user);
        model.addAttribute("anime", anime);
        return "home";
    }

    @GetMapping(path = "/info/{id}")
    public String info(Model model,
                       Authentication auth,
                       @PathVariable Long id) {
        Anime anime = animeRepository.getOne(id);
        Set<Video> video = anime.getVideo();
        User user = userRepository.findByUsername(auth.getName());

        model.addAttribute("user", user);
        model.addAttribute("anime", anime);
        model.addAttribute("video", video);
        return "info";
    }

    @GetMapping(path = "/video/{id_anime}/{id_season}/{id_ep}")
    public String video(Model model,
                        Authentication auth,
                        @PathVariable Long id_anime,
                        @PathVariable int id_season,
                        @PathVariable int id_ep) {
        User user = userRepository.findByUsername(auth.getName());
        Anime anime = animeRepository.getOne(id_anime);
        List<Comment> comment = commentRepository.findAllByAnimeOrderByIdDesc(anime);
        Set<Video> video = anime.getVideo();
        for (Video v : video) {
            if (v.getSeason() == id_season) {
                model.addAttribute("video", v);
            }
        }

        model.addAttribute("comment", comment);
        model.addAttribute("user", user);
        model.addAttribute("anime", anime);
        model.addAttribute("id_season", id_season);
        model.addAttribute("id_ep", id_ep);

        return "video";
    }

    @PostMapping("sendcomment/{user_id}/{anime_id}/{id_season}/{id_ep}")
    public String sendComment(@PathVariable Long user_id,
                              @PathVariable Long anime_id,
                              @PathVariable Long id_season,
                              @PathVariable Long id_ep,
                              @RequestParam String comment) {

        User user = userRepository.getOne(user_id);
        Anime anime = animeRepository.getOne(anime_id);
        Date date= new Date();
        long time = date.getTime();
        Comment com = new Comment(null, comment, new Timestamp(time), user, anime);

        commentRepository.save(com);

        return "redirect:/video/"+anime_id+'/'+id_season+'/'+id_ep;
    }

    @GetMapping("delete_comment/{id}/{id_anime}/{id_season}/{id_ep}")
    public String deleteComment(@PathVariable Long id,
                                @PathVariable Long id_anime,
                                @PathVariable Long id_season,
                                @PathVariable Long id_ep) {
        commentRepository.deleteById(id);
        return "redirect:/video/"+id_anime+'/'+id_season+'/'+id_ep;
    }

    @GetMapping("admin_anime")
    public String adminAnime(Model model, Authentication auth) {

        List<Anime> anime = animeRepository.findAll();
        User user = userService.findByUsername(auth.getName());

        model.addAttribute("user", user);
        model.addAttribute("anime", anime);
        return "adminAnime";
    }

    @GetMapping("delete_anime/{id}")
    public String deleteAnime(@PathVariable Long id) {
        animeRepository.deleteById(id);
        return "redirect:/admin_anime";
    }
}
