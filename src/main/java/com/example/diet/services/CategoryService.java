package com.example.diet.services;

import com.example.diet.commands.CategoryCommand;
import com.example.diet.domains.Category;

import java.util.Set;

public interface CategoryService {

    Set<Category> getCategories();
    Category findById(Long l);
    CategoryCommand saveCategoryCommand (CategoryCommand command);
    CategoryCommand findCommandById(Long l);
    void deleteById(Long idToDelete);
}
