package com.example.truyen.Repository;

import com.example.truyen.Entity.Chapter;
import com.example.truyen.Entity.ChapterPage;
import com.example.truyen.Entity.Comic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChapterRepository extends JpaRepository<Chapter, Long> {
    Chapter findTopByComicOrderByCreatedAtDesc(Comic comic);
    Optional<Chapter> findById(Long id);
    List<Chapter> findByComicIdOrderByCreatedAtDesc(Long comicId);
    Chapter findByComicIdAndChapterNumber(Long comicId, Integer chapterNumber);
}