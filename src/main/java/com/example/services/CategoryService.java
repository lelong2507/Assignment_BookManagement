package com.example.services;

import java.util.List;

import com.example.model.Category;

public interface CategoryService {
    void addCategory(Category category);
    List<Category> showListCategory();
    Category updateCategory(int idCategory, Category category);
    void deleteCategory(int idCategory);
}
