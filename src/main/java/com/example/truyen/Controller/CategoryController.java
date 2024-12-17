package com.example.truyen.Controller;

import com.example.truyen.Entity.Category;
import com.example.truyen.Service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CategoryController {

    @Autowired
    private CategoryService categoryService;


    @GetMapping("/category")
    public String getAllCategory() {
        try {
            categoryService.getAllCategory();
            return "category/index";
        } catch (Exception e) {
            return "error";
        }
    }

    @GetMapping("/category/detail")
    public String getCategoryDetail(Long id) {
        categoryService.getCategoryDetail(id);
        return "category/detail";
    }

    @GetMapping("/category/create")
    public String createCategory(Category category) {
        categoryService.createCategory(category);
        return "category/create";
    }

    @GetMapping("/category/edit")
    public String editCategory(Category category) {
        categoryService.editCategory(category);
        return "category/edit";
    }

    @GetMapping("/category/delete")
    public String deleteCategory(Long id) {
        categoryService.deleteCategory(id);
        return "category/delete";
    }
}
