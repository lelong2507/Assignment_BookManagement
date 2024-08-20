package com.example.services.BookServiceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.Category;
import com.example.repository.CategoryRepository;
import com.example.services.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public void addCategory(Category category) {
        categoryRepository.save(category);
    }

    @Override
    public List<Category> showListCategory() {
        List<Category> cateList = categoryRepository.findAll();
       
        return cateList;
    }

    @Override
    public Category updateCategory(int idCategory, Category category) {
        Optional<Category> getCate = categoryRepository.findById(idCategory);
        if(getCate.isPresent()){
            category = getCate.get();
            return categoryRepository.save(category);
        } else {
            return null;
        }
    }

    @Override
    public void deleteCategory(int idCategory) {
        Optional<Category> getCate = categoryRepository.findById(idCategory);
        if (getCate.isPresent()) {
            Category category = getCate.get();
            categoryRepository.delete(category);
        }else {
            System.out.println("The category doesn't have");
        }
    }
}
