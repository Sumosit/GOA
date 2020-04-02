package com.example.java.lab3.repositories;

import com.example.java.lab3.entities.Anime;
import com.example.java.lab3.entities.Bookmark;
import com.example.java.lab3.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {
    Set<Bookmark> findAllByUser(User user);
    Set<Bookmark> findAllByUserAndAnime(User user, Anime anime);
}
