package com.example.diet.controllers;


import com.example.diet.services.CategoryService;
import com.example.diet.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j // logger
@Controller
public class IndexController {


    private final CategoryService categoryService;

    @Autowired
    public IndexController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @RequestMapping({"", "/", "/index"})
    public String getIndexPage(Model model){

        log.debug("Getting Index page");
        model.addAttribute("categories", categoryService.getCategories());
        return "index";
    }
}
