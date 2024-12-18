package com.example.truyen.Service;

import com.example.truyen.Entity.Comic;
import com.example.truyen.Repository.ComicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ComicService {
    @Autowired
    private ComicRepository comicRepository;

    public Comic getComicById(Long id) {
        return comicRepository.findById(id).orElse(null);
    }
}