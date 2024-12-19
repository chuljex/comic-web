package com.example.truyen.Controller;

import com.example.truyen.DTO.ComicFormDto;
import com.example.truyen.Entity.Comic;
import com.example.truyen.Entity.ComicStatus;
import com.example.truyen.Entity.Country;
import com.example.truyen.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
public class UserController {
    @Autowired
    private ComicService comicService;

    @Autowired
    private ComicCategoryService comicCategoryService;

    @Autowired
    private CountryService countryService;

    @Autowired
    private ComicStatusService comicStatusService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/user/userProfile")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_CONTRIBUTOR', 'ROLE_USER')")
    @ResponseBody
    public String userProfile() {
        return "Welcome to User Profile";
    }

    @GetMapping("/contributor/contributorProfile")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_CONTRIBUTOR')")
    @ResponseBody
    public String contributorProfile() {
        return "Welcome to Contributor Profile";
    }

    @GetMapping("/contributor/upload-comic")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_CONTRIBUTOR')")
    public String showUploadComicForm(Model model) {
        model.addAttribute("comicForm", new ComicFormDto());
        model.addAttribute("categories", categoryService.getAllCategory());
        model.addAttribute("countries", countryService.getAllCountry());
        model.addAttribute("comicStatus", comicStatusService.getAllComicStatus());
        return "form/upload-comic";
    }

    @PostMapping("/contributor/upload-comic")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_CONTRIBUTOR')")
    public String addNewComic(@ModelAttribute("comicForm") ComicFormDto comicForm) {
        comicService.addComic(comicForm.getComic());
        comicCategoryService.addComicCategories(comicForm.getSelectedComicCategories(), comicForm.getComic());
        return "redirect:/contributor/contributorProfile";
    }

    @GetMapping("/admin/adminProfile")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @ResponseBody
    public String adminProfile() {
        return "Welcome to Admin Profile";
    }
}
