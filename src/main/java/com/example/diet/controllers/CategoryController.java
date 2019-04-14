package com.example.diet.controllers;

import com.example.diet.services.CategoryService;
import com.example.diet.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Slf4j
@Controller
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/category/{id}/show")
    public String showById(@PathVariable String id, Model model){

        model.addAttribute("recipies", categoryService.findById(new Long(id)).getRecipes());

        return "category/show";
    }


}
