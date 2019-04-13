package com.example.diet.bootstrap;

import com.example.diet.domains.Category;
import com.example.diet.domains.Recipe;
import com.example.diet.domains.UnitOfMeasure;
import com.example.diet.repositories.CategoryRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class RecipeBootstrap  implements ApplicationListener<ContextRefreshedEvent> {

    private final CategoryRepository categoryRepository;

    public RecipeBootstrap(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        categoryRepository.saveAll(getCategories());

    }

    private List<Category> getCategories() {

        List<Category> categories = new ArrayList<>();

        //get Categories
        Optional<Category> breakfastCategoryOptional = categoryRepository.findByDescription("Breakfast");

        if(!breakfastCategoryOptional.isPresent()){
            throw new RuntimeException("Expected Category Not Found");
        }

        Optional<Category> dinnerCategoryOptional = categoryRepository.findByDescription("Dinner");

        if(!dinnerCategoryOptional.isPresent()){
            throw new RuntimeException("Expected Category Not Found");
        }

        Optional<Category> dessertCategoryOptional = categoryRepository.findByDescription("Dessert");

        if(!dessertCategoryOptional.isPresent()){
            throw new RuntimeException("Expected Category Not Found");
        }

        Category breakfastCategory = breakfastCategoryOptional.get();
        Category dinnernCategory = dinnerCategoryOptional.get();
        Category dessertCategory = dessertCategoryOptional.get();

        return categories;

    }
}
