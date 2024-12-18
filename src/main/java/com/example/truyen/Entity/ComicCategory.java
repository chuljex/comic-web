package com.example.truyen.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "comic_category")
public class ComicCategory {
    @Id
    @ManyToOne
    @JoinColumn(name = "comic_id", nullable = false)
    private Comic comic;

    @Id
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;
}
