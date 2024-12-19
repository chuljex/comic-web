package com.example.truyen.Repository;

import com.example.truyen.Entity.ChapterPage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChapterPageRepository extends JpaRepository<ChapterPage, Long> {
    List<ChapterPage> findByChapterIdOrderByPageNumber(Long chapterId);
    List<ChapterPage> findByChapterIdOrderByPageNumberAsc(Long chapterId);
}