package com.example.truyen.Controller;

import com.example.truyen.Service.ComicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/comics")
public class ComicController {
    @Autowired
    private ComicService comicService;

    @GetMapping
    public String listComics(Model model) {
        model.addAttribute("comics", comicService.getAllComics());
        return "comics/list";
    }

    @GetMapping("/{id}")
    public String comicDetails(@PathVariable Long id, Model model) {
        model.addAttribute("comic", comicService.getComicById(id));
        return "comics/details";
    }
}