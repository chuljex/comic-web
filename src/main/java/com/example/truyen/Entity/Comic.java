package com.example.truyen.Entity;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Comics")
public class Comic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String author;
    private String description;
    private String thumbnail;
    private String status;
    private int view;

    @OneToMany(mappedBy = "comic", cascade = CascadeType.ALL)
    private List<Chapter> chapters = new ArrayList<>();
}
