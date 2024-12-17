package com.example.truyen.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "account_status")
public class AccountStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String title;

    @Column(name = "restricted_level", nullable = false)
    private int restrictedLevel;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getRestrictedLevel() {
        return restrictedLevel;
    }

    public void setRestrictedLevel(int restrictedLevel) {
        this.restrictedLevel = restrictedLevel;
    }
}