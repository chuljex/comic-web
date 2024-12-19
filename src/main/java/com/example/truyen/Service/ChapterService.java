package com.example.truyen.Service;

import com.example.truyen.Entity.Chapter;
import com.example.truyen.Entity.ChapterPage;
import com.example.truyen.Repository.ChapterPageRepository;
import com.example.truyen.Repository.ChapterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChapterService {
    @Autowired
    private ChapterRepository chapterRepository;

    @Autowired
    private ChapterPageRepository chapterPageRepository;

    public Chapter getChapterById(Long id) {
        return chapterRepository.findById(id).orElse(null);
    }

    public List<Chapter> getChaptersByComicId(Long comicId) {
        // Lấy danh sách các chương từ ComicRepository và sắp xếp theo ngày tạo giảm dần
        List<Chapter> chapters = chapterRepository.findByComicIdOrderByCreatedAtDesc(comicId);
        return chapters;
    }

    public Chapter getChapterByComicIdAndChapterNumber(Long comicId, Integer chapterNumber) {
        return chapterRepository.findByComicIdAndChapterNumber(comicId, chapterNumber);
    }

    public List<ChapterPage> getPagesByChapterId(Long chapterId) {
        return chapterPageRepository.findByChapterIdOrderByPageNumberAsc(chapterId);
    }
}