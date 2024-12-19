package com.example.truyen.Service;

import com.example.truyen.Entity.ChapterPage;
import com.example.truyen.Repository.ChapterPageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChapterPageService {
    @Autowired
    private ChapterPageRepository chapterPageRepository;

    public void saveChapterPage(ChapterPage chapterPage) {
        chapterPageRepository.save(chapterPage);
    }
}