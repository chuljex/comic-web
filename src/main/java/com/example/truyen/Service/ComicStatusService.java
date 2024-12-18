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
        // Lấy danh sách tất cả các status
        var data = comicStatusRepository.findAll();
        return data;
    }
}
