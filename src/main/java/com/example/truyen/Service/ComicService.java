package com.example.truyen.Service;

import com.example.truyen.Entity.Comic;
import com.example.truyen.Repository.ComicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@Service
public class ComicService {
    @Autowired
    private ComicRepository comicRepository;

    public Comic getComicById(Long id) {
        return comicRepository.findById(id).orElse(null);
    }

    public String addComic(Comic comicInfo) {
        comicInfo.setViews(0);

        ZoneId zone = ZoneId.of("Asia/Ho_Chi_Minh");
        LocalDateTime currentDateAndTime = LocalDateTime.now(zone);
        comicInfo.setCreatedAt(currentDateAndTime);
        comicInfo.setUpdatedAt(currentDateAndTime);

        // System.out.print(comicInfo);

        comicRepository.save(comicInfo);
        return "Comic Added Successfully";
    }

    public List<Comic> getAllComics() {
        return comicRepository.findAll();
    }

    public List<Comic> filterComics(Long categoryId, Integer status, Integer country) {
        return comicRepository.findByFilters(categoryId, status, country);
    }
}
