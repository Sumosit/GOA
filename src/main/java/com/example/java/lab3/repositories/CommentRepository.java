package com.example.java.lab3.repositories;

import com.example.java.lab3.entities.Anime;
import com.example.java.lab3.entities.Comment;
import com.example.java.lab3.entities.Video;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByAnimeOrderByIdDesc(Anime anime);
}
