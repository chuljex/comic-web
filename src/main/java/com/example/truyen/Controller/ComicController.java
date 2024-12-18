package com.example.truyen.Controller;

import com.example.truyen.Entity.Chapter;
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
}