package com.example.java.lab3.repositories;

import com.example.java.lab3.entities.Anime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.awt.print.Pageable;
import java.util.List;

public interface AnimeRepository extends JpaRepository<Anime, Long> {
    List<Anime> findByNameContainingIgnoreCase(String name);
    List<Anime> findByName(String name);
    Anime findOneByName(String name);
}
