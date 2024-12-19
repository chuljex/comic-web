package com.example.truyen.Service;

import com.example.truyen.Entity.Category;
import com.example.truyen.Repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getAllCategory() {
        try {
            var data = categoryRepository.findAll();

            if (data.isEmpty()) {
                throw new RuntimeException("No data found");
            }
            
            return data;
        } catch (Exception e) {
            throw new RuntimeException("Error =====: " + e.getMessage());
        }
    }

    public void getCategoryDetail(Long id) {
        // Lấy chi tiết một category
        categoryRepository.findById(id);
    }

    public void createCategory(Category category) {
        // Thêm mới một category
        categoryRepository.save(category);
    }


    public void editCategory(Category category) {
        // Cập nhật một category
        categoryRepository.save(category);
    }

    public void deleteCategory(Long id) {
        // Xóa một category
        categoryRepository.deleteById(id);
    }
}
