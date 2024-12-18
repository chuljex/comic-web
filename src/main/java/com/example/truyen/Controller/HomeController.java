package com.example.truyen.Controller;

import com.example.truyen.Entity.Category;
import com.example.truyen.Entity.Chapter;
import com.example.truyen.Entity.Comic;
import com.example.truyen.Repository.ChapterRepository;
import com.example.truyen.Repository.ComicRepository;
import com.example.truyen.Utility.TimeAgo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class HomeController {
    @Autowired
    private ComicRepository comicRepository;

    @Autowired
    private ChapterRepository chapterRepository;

    @GetMapping("/")
    public String listComics(Model model) {
        // 1. Lấy 10 comics có lượt xem trên 10,000 và sắp xếp theo lượt xem giảm dần
        List<Comic> comicsByViews = comicRepository.findByViewsGreaterThanOrderByViewsDesc(10000);
        // Giới hạn danh sách lấy chỉ 10 comic
        List<Comic> topComics = comicsByViews.stream().limit(10).collect(Collectors.toList());

        // 2. Lấy 24 comics đã có chapter mới nhất, sắp xếp theo thời gian chapter mới nhất
        List<Comic> comicsWithLatestChapters = comicRepository.findAll(); // Lấy tất cả comics
        Map<Long, Chapter> latestChapters = new HashMap<>();
        Map<Long, List<Category>> comicCategories = new HashMap<>();
        for (Comic comic : comicsWithLatestChapters) {
            Chapter latestChapter = chapterRepository.findTopByComicOrderByCreatedAtDesc(comic);
            if (latestChapter != null) {
                latestChapters.put(comic.getId(), latestChapter);
            }
        }

        // Sắp xếp danh sách comics theo thời gian chapter mới nhất
        List<Comic> sortedByLatestChapter = new ArrayList<>(comicsWithLatestChapters);
        sortedByLatestChapter.sort((comic1, comic2) -> {
            Chapter latestChapter1 = latestChapters.get(comic1.getId());
            Chapter latestChapter2 = latestChapters.get(comic2.getId());
            if (latestChapter1 != null && latestChapter2 != null) {
                return latestChapter2.getCreatedAt().compareTo(latestChapter1.getCreatedAt());
            }
            return 0;
        });
        // Giới hạn danh sách lấy chỉ 24 comic
        List<Comic> latestComics = sortedByLatestChapter.stream().limit(24).collect(Collectors.toList());

        // Tính toán thời gian đã qua cho mỗi chapter mới nhất
        Map<Long, String> timeAgoMap = new HashMap<>();
        for (Comic comic : latestComics) {
            Chapter latestChapter = latestChapters.get(comic.getId());
            if (latestChapter != null) {
                timeAgoMap.put(comic.getId(), TimeAgo.calculateTimeAgo(latestChapter.getCreatedAt()));
            }
        }

        // Truyền dữ liệu vào model
        model.addAttribute("topComics", topComics);
        model.addAttribute("latestComics", latestComics);
        model.addAttribute("latestChapters", latestChapters);
        model.addAttribute("timeAgoMap", timeAgoMap);

        return "home/index"; // Tên template
    }
}
