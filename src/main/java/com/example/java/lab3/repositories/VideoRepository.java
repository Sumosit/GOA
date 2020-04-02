package com.example.java.lab3.repositories;

import com.example.java.lab3.entities.Anime;
import com.example.java.lab3.entities.Video;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VideoRepository extends JpaRepository<Video, Long> {
    Video findBySeason(int i);
}
