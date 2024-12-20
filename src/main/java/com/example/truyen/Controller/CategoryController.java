package com.example.truyen.Controller;

import com.example.truyen.Entity.Category;
import com.example.truyen.Entity.Comic;
import com.example.truyen.Entity.ComicStatus;
import com.example.truyen.Entity.Country;
import com.example.truyen.Service.CategoryService;
import com.example.truyen.Service.ComicService;
import com.example.truyen.Service.ComicStatusService;
import com.example.truyen.Service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @Autowired
    private ComicService comicService;

    @GetMapping("/{id}")
    public String getCategoryById(
            @PathVariable Long id,
            @RequestParam(required = false) Integer status,
            @RequestParam(required = false) Integer country,
            Model model
    ) {
        List<Category> categories = categoryService.getAllCategory();
        Category category = categoryService.getCategoryById(id);
        List<ComicStatus> comicStatuses = comicStatusService.getAllComicStatus();
        List<Country> countries = countryService.getAllCountry();

        model.addAttribute("selectedCategoryId", id);
        model.addAttribute("categories", categories);
        model.addAttribute("category", category);
        model.addAttribute("comicStatuses", comicStatuses);
        model.addAttribute("countries", countries);

        filterComics(id, status, country, model);
        return "category/detail";
    }

    public void filterComics(
            @PathVariable Long categoryId,
            @RequestParam(required = false) Integer status,
            @RequestParam(required = false) Integer country,
            Model model) {

        List<Comic> comics = comicService.filterComics(categoryId, status, country);

        model.addAttribute("comics", comics);
        model.addAttribute("categoryId", categoryId);
        model.addAttribute("status", status);
        model.addAttribute("country", country);
    }
}
