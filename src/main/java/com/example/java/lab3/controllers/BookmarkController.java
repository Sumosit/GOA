package com.example.java.lab3.controllers;

import com.example.java.lab3.entities.Anime;
import com.example.java.lab3.entities.Bookmark;
import com.example.java.lab3.entities.User;
import com.example.java.lab3.repositories.AnimeRepository;
import com.example.java.lab3.repositories.BookmarkRepository;
import com.example.java.lab3.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Set;

@Controller
public class BookmarkController {

    @Autowired
    UserService userService;

    @Autowired
    BookmarkRepository bookmarkRepository;

    @Autowired
    AnimeRepository animeRepository;

    @GetMapping("bookmark")
    public String Bookmark(Model model,
                           Authentication auth) {

        User user = userService.findByUsername(auth.getName());
        Set<Bookmark> bk = bookmarkRepository.findAllByUser(user);

        model.addAttribute("user", user);
        model.addAttribute("bk", bk);

        return "bookmark";
    }

    @GetMapping("addtobookmark/{id_anime}/{id_season}/{id_ep}")
    public String addToBookmark(@PathVariable Long id_anime,
                                @PathVariable Long id_season,
                                @PathVariable Long id_ep,
                                Authentication auth, Model model) {

        User user = userService.findByUsername(auth.getName());
        Anime anime = animeRepository.getOne(id_anime);
        Bookmark bk = new Bookmark(null, user, anime, id_anime, id_season, id_ep);
        bookmarkRepository.save(bk);

        return "redirect:/video/"+id_anime+'/'+id_season+'/'+id_ep;
    }

    @GetMapping("bookmarksearch")
    public String searchBookmark(Model model,
                                 Authentication auth,
                                 @RequestParam String name) {
        User user = userService.findByUsername(auth.getName());
        Anime anime = animeRepository.findOneByName(name);
        Set<Bookmark> bk = bookmarkRepository.findAllByUserAndAnime(user, anime);

        model.addAttribute("user", user);
        model.addAttribute("bk", bk);

        return "bookmark";
    }
}
