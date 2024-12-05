package com.example.truyen.Service;

import com.example.truyen.Entity.Comic;
import com.example.truyen.Repository.ComicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComicService {
    @Autowired
    private ComicRepository comicRepository;

    public List<Comic> getAllComics() {
        return comicRepository.findAll();
    }

    public Comic getComicById(Long id) {
        return comicRepository.findById(id).orElseThrow(() -> new RuntimeException("Comic not found"));
    }
}
