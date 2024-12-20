package com.example.truyen.Service;

import com.example.truyen.Entity.ComicStatus;
import com.example.truyen.Repository.ComicStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComicStatusService {
    @Autowired
    private ComicStatusRepository comicStatusRepository;

    public List<ComicStatus> getAllComicStatus() {
        try {
            var data = comicStatusRepository.findAll();

            if (data.isEmpty()) {
                throw new RuntimeException("No data found");
            }

            return data;
        } catch (Exception e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }
}
