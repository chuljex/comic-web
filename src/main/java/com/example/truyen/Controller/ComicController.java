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
import org.springframework.web.bind.annotation.ResponseBody;

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

        // Lấy chương đầu tiên
        Chapter firstChapter = chapters.isEmpty() ? null : chapters.get(chapters.size() - 1);

        model.addAttribute("comic", comic);
        model.addAttribute("chapters", chapters);
        model.addAttribute("firstChapter", firstChapter);
        return "comic/index";
    }

    @GetMapping("/{comicId}/chapter-{chapterId}")
    public String getChapterContent(@PathVariable Long comicId, @PathVariable Integer chapterId, Model model) {
        Chapter chapter = chapterService.getChapterByComicIdAndId(comicId, chapterId);
        if (chapter != null) {
            List<ChapterPage> pages = chapterService.getPagesByChapterId(chapter.getId());

            // Tìm chương kế tiếp và chương trước đó
            Chapter prevChapter = chapterService.getPreviousChapter(comicId, chapterId);
            Chapter nextChapter = chapterService.getNextChapter(comicId, chapterId);

            boolean hasPrevChapter = (prevChapter != null);
            boolean hasNextChapter = (nextChapter != null);

            model.addAttribute("chapter", chapter);
            model.addAttribute("pages", pages);

            model.addAttribute("prevChapter", prevChapter);
            model.addAttribute("nextChapter", nextChapter);
            model.addAttribute("hasPrevChapter", hasPrevChapter);
            model.addAttribute("hasNextChapter", hasNextChapter);
        }
        return "comic/detail";
    }
}