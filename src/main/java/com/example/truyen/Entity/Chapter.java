package com.example.truyen.Entity;
import jakarta.persistence.*;

@Entity
@Table(name = "Chapters")
public class Chapter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int chapterNumber;
    private String title;
    private String content;

    @ManyToOne
    @JoinColumn(name = "comic_id")
    private Comic comic;
}
