package com.example.truyen.Controller;

import com.example.truyen.Entity.Chapter;
import com.example.truyen.Entity.ChapterPage;
import com.example.truyen.Entity.Comic;
import com.example.truyen.Service.ChapterService;
import com.example.truyen.Service.ComicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/album")
public class ComicController {
    @Autowired
    private ComicService comicService;

    @Autowired
    private ChapterService chapterService;

    @GetMapping("/{id}")
    public String getComicDetails(@PathVariable Long id, Model model) {
        Comic comic = comicService.getComicById(id);
        // Lấy danh sách các chương đã được sắp xếp từ mới nhất đến cũ nhất
        List<Chapter> chapters = chapterService.getChaptersByComicId(id);
        model.addAttribute("comic", comic);
        model.addAttribute("chapters", chapters);
        return "comic/index";
    }

    @GetMapping("/{comicId}/chapter-{chapterNumber}")
    public String getChapterContent(@PathVariable Long comicId, @PathVariable Integer chapterNumber, Model model) {
        Chapter chapter = chapterService.getChapterByComicIdAndChapterNumber(comicId, chapterNumber);
        if (chapter != null) {
            List<ChapterPage> pages = chapterService.getPagesByChapterId(chapter.getId());

            // Tìm chương kế tiếp và chương trước đó
            Chapter prevChapter = chapterService.getChapterByComicIdAndChapterNumber(comicId, chapterNumber - 1);
            Chapter nextChapter = chapterService.getChapterByComicIdAndChapterNumber(comicId, chapterNumber + 1);

            model.addAttribute("chapter", chapter);
            model.addAttribute("pages", pages);

            model.addAttribute("prevChapter", prevChapter);
            model.addAttribute("nextChapter", nextChapter);
        }
        return "comic/detail";
    }
}