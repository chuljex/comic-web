package com.example.truyen.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "user_favorites")
public class UserFavorite {

    @Id
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Id
    @ManyToOne
    @JoinColumn(name = "comic_id", nullable = false)
    private Comic comic;

    // Getters and Setters
    public void setUser(User user) {
        this.user = user;
    }

    public Comic getComic() {
        return comic;
    }

    public void setComic(Comic comic) {
        this.comic = comic;
    }
}