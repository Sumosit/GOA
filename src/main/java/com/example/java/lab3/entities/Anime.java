package com.example.java.lab3.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.attoparser.dom.Text;

import javax.persistence.*;
import java.awt.*;
import java.sql.Timestamp;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "anime")
public class Anime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int views, season;
    private String history, links;
    private String name, image, genre, date, producer, address;
    private Timestamp birth;

    @ManyToMany(fetch = FetchType.LAZY )
    private Set<Video> video;

    @OneToOne(fetch = FetchType.LAZY)
    private User author;
}
