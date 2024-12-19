package com.example.truyen.Service;

import com.example.truyen.Entity.Category;
import com.example.truyen.Entity.Comic;
import com.example.truyen.Entity.ComicCategory;
import com.example.truyen.Repository.ComicCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComicCategoryService {
    @Autowired
    private ComicCategoryRepository comicCategoryRepository;

    public String addComicCategories(List<Category> comicCategories, Comic comic) {
        for (Category category : comicCategories) {
            ComicCategory value = new ComicCategory();
            value.setComic(comic);
            value.setCategory(category);
//            System.out.print(value);
            comicCategoryRepository.save(value);
        }
        return "Comic Categories Added Successfully";
    }
}
