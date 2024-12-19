package com.example.truyen.Service;

import com.example.truyen.Entity.Comic;
import com.example.truyen.Repository.ComicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Service
public class ComicService {
    @Autowired
    private ComicRepository comicRepository;

    public String addComic(Comic comicInfo) {
        comicInfo.setViews(0);

        ZoneId zone = ZoneId.of("Asia/Ho_Chi_Minh");
        LocalDateTime currentDateAndTime = LocalDateTime.now(zone);
        comicInfo.setCreatedAt(currentDateAndTime);
        comicInfo.setUpdatedAt(currentDateAndTime);

//        System.out.print(comicInfo);

        comicRepository.save(comicInfo);
        return "Comic Added Successfully";
    }
}
