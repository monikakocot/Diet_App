package com.example.diet.services;

import com.example.diet.commands.CategoryCommand;
import com.example.diet.commands.RecipeCommand;
import com.example.diet.converters.CategoryCommandToCategory;
import com.example.diet.converters.CategoryToCategoryCommand;
import com.example.diet.domains.Category;
import com.example.diet.domains.Recipe;
import com.example.diet.repositories.CategoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.example.diet.exceptions.NotFoundException;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryCommandToCategory categoryCommandToCategory;
    private final CategoryToCategoryCommand categoryToCategoryCommand;

    public CategoryServiceImpl(CategoryRepository categoryRepository,
                               CategoryCommandToCategory categoryCommandToCategory,
                               CategoryToCategoryCommand categoryToCategoryCommand) {
        this.categoryRepository = categoryRepository;
        this.categoryCommandToCategory = categoryCommandToCategory;
        this.categoryToCategoryCommand = categoryToCategoryCommand;
    }

    @Override
    public Set<Category> getCategories() {

        Set<Category> categoriesSet = new HashSet<>();
        categoryRepository.findAll().iterator().forEachRemaining(categoriesSet::add);
        return categoriesSet;
    }

    @Override
    public Category findById(Long l) {
        Optional<Category> categoryOptional = categoryRepository.findById(l);

        if (!categoryOptional.isPresent()) {
            //throw new RuntimeException("Recipe Not Found!");
            throw new NotFoundException("Recipe Not Found. For ID value: " + l.toString());
        }

        return categoryOptional.get();
    }

    @Override
    @Transactional
    public CategoryCommand saveCategoryCommand(CategoryCommand command) {
        Category detachedCategory = categoryCommandToCategory.convert(command);

        Category savedCategory = categoryRepository.save(detachedCategory);
        log.debug("Saved CategoryId:" + savedCategory.getId());
        return categoryToCategoryCommand.convert(savedCategory);
    }


    @Override
    public CategoryCommand findCommandById(Long l) {
        return categoryToCategoryCommand.convert(findById(l));
    }

    @Override
    public void deleteById(Long idToDelete) {

    }
}
