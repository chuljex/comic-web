package com.example.truyen.DTO;

import com.example.truyen.Entity.Comic;
import com.example.truyen.Entity.Category;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ComicFormDto {
    private Comic comic;
    private List<Category> selectedComicCategories;
}
