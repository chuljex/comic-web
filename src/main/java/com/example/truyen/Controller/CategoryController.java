package com.example.truyen.Controller;

import com.example.truyen.Entity.Category;
import com.example.truyen.Entity.ComicStatus;
import com.example.truyen.Entity.Country;
import com.example.truyen.Service.CategoryService;
import com.example.truyen.Service.ComicStatusService;
import com.example.truyen.Service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ComicStatusService comicStatusService;

    @Autowired
    private CountryService countryService;

    @GetMapping("/{id}")
    public String getCategoryById(@PathVariable Long id, Model model) {
        Category category = categoryService.getCategoryById(id);
        List<ComicStatus> comicStatuses = comicStatusService.getAllComicStatus();
        List<Country> countries = countryService.getAllCountry();

        model.addAttribute("category", category);
        model.addAttribute("comicStatuses", comicStatuses);
        model.addAttribute("countries", countries);
        return "category/detail";
    }
}
