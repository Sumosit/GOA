package com.example.java.lab3.controllers;

import com.example.java.lab3.entities.Anime;
import com.example.java.lab3.entities.Friend;
import com.example.java.lab3.repositories.AnimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/twitter")
public class FriendsController {

    @Autowired
    AnimeRepository animeRepository;

    private List<Anime> animes;

    public FriendsController() {

    }

    @GetMapping()
    public List<Anime> list() {
        return animes;
    }

}
