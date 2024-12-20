package com.example.truyen.Controller;

import com.example.truyen.Entity.Chapter;
import com.example.truyen.Entity.Comic;
import com.example.truyen.Repository.ChapterRepository;
import com.example.truyen.Service.ComicService;
import com.example.truyen.Utility.TimeAgo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class SearchController {

    @Autowired
    private ComicService comicService;

    @Autowired
    private ChapterRepository chapterRepository;

    @GetMapping("/search")
    public String search(@RequestParam("query") String query, Model model) {
        List<Comic> searchResults = comicService.searchComics(query);
        Map<Long, String> timeAgoMap = new HashMap<>();
        Map<Long, Chapter> latestChapters = new HashMap<>();

        for (Comic comic : searchResults) {
            Chapter latestChapter = chapterRepository.findTopByComicOrderByCreatedAtDesc(comic);
            if (latestChapter != null) {
                latestChapters.put(comic.getId(), latestChapter);
                timeAgoMap.put(comic.getId(), TimeAgo.calculateTimeAgo(latestChapter.getCreatedAt()));
            }
        }

        model.addAttribute("searchResults", searchResults);
        model.addAttribute("latestChapters", latestChapters);
        model.addAttribute("timeAgoMap", timeAgoMap);

        return "search/index";
    }
}