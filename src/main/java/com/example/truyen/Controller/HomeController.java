package com.example.truyen.Controller;

import com.example.truyen.Service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public String home(Model model) {
        var categories = categoryService.getAllCategory();
        model.addAttribute("categories", categories);
        return "home/index"; // Trả về template home/index.html
    }
}
