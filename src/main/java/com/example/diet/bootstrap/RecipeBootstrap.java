package com.example.diet.bootstrap;

import com.example.diet.domains.*;
import com.example.diet.repositories.CategoryRepository;
import com.example.diet.repositories.RecipeRepository;
import com.example.diet.repositories.UnitOfMeasureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class RecipeBootstrap  implements ApplicationListener<ContextRefreshedEvent> {

    private final CategoryRepository categoryRepository;
    private final RecipeRepository recipeRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    @Autowired
    public RecipeBootstrap(CategoryRepository categoryRepository, RecipeRepository recipeRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.recipeRepository = recipeRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }
    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        categoryRepository.saveAll(getCategories());
        recipeRepository.saveAll(getRecipes());
//
    }

    private List<Recipe> getRecipes() {

        List<Recipe> recipes = new ArrayList<>();

        //get UOMs
        Optional<UnitOfMeasure> eachUomOptional = unitOfMeasureRepository.findByDescription("Each");

        if(!eachUomOptional.isPresent()){
            throw new RuntimeException("Expected UOM Not Found");
        }

        Optional<UnitOfMeasure> tableSpoonUomOptional = unitOfMeasureRepository.findByDescription("Tablespoon");

        if(!tableSpoonUomOptional.isPresent()){
            throw new RuntimeException("Expected UOM Not Found");
        }

        Optional<UnitOfMeasure> teaSpoonUomOptional = unitOfMeasureRepository.findByDescription("Teaspoon");

        if(!teaSpoonUomOptional.isPresent()){
            throw new RuntimeException("Expected UOM Not Found");
        }

        Optional<UnitOfMeasure> dashUomOptional = unitOfMeasureRepository.findByDescription("Dash");

        if(!dashUomOptional.isPresent()){
            throw new RuntimeException("Expected UOM Not Found");
        }

        Optional<UnitOfMeasure> pintUomOptional = unitOfMeasureRepository.findByDescription("Pint");

        if(!pintUomOptional.isPresent()){
            throw new RuntimeException("Expected UOM Not Found");
        }

        Optional<UnitOfMeasure> cupsUomOptional = unitOfMeasureRepository.findByDescription("Cup");

        if(!cupsUomOptional.isPresent()){
            throw new RuntimeException("Expected UOM Not Found");
        }

        //get optionals
        UnitOfMeasure eachUom = eachUomOptional.get();
        UnitOfMeasure tableSpoonUom = tableSpoonUomOptional.get();
        UnitOfMeasure teapoonUom = tableSpoonUomOptional.get();
        UnitOfMeasure dashUom = dashUomOptional.get();
        UnitOfMeasure pintUom = dashUomOptional.get();
        UnitOfMeasure cupsUom = cupsUomOptional.get();


        //Guacamole
        Recipe guacRecipe = new Recipe();
        guacRecipe.setDescription("Guacamole");
        guacRecipe.setTotalTime(20);
        guacRecipe.setCalories(300);
        guacRecipe.setDifficulty(Difficulty.EASY);
        guacRecipe.setDirections("1 Cut avocado, remove flesh: Cut the avocados in half. Remove seed. Score the inside of the avocado with a blunt knife and scoop out the flesh with a spoon" +
                "\n" +
                "2 Mash with a fork: Using a fork, roughly mash the avocado. (Don't overdo it! The guacamole should be a little chunky.)" +
                "\n" +
                "3 Add salt, lime juice, and the rest: Sprinkle with salt and lime (or lemon) juice. The acid in the lime juice will provide some balance to the richness of the avocado and will help delay the avocados from turning brown.\n" +
                "Add the chopped onion, cilantro, black pepper, and chiles. Chili peppers vary individually in their hotness. So, start with a half of one chili pepper and add to the guacamole to your desired degree of hotness.\n" +
                "Remember that much of this is done to taste because of the variability in the fresh ingredients. Start with this recipe and adjust to your taste.\n" +
                "4 Cover with plastic and chill to store: Place plastic wrap on the surface of the guacamole cover it and to prevent air reaching it. (The oxygen in the air causes oxidation which will turn the guacamole brown.) Refrigerate until ready to serve.\n" +
                "Chilling tomatoes hurts their flavor, so if you want to add chopped tomato to your guacamole, add it just before serving.\n" +
                "\n" +
                "\n" +
                "Read more: http://www.simplyrecipes.com/recipes/perfect_guacamole/#ixzz4jvpiV9Sd");

        //very redundent - could add helper method, and make this simpler
        guacRecipe.addIngredient(new Ingredient("ripe avocados", new BigDecimal(2), eachUom));
        guacRecipe.addIngredient(new Ingredient("Kosher salt", new BigDecimal(".5"), teapoonUom));
        guacRecipe.addIngredient(new Ingredient("fresh lime juice or lemon juice", new BigDecimal(2), tableSpoonUom));
        guacRecipe.addIngredient(new Ingredient("minced red onion or thinly sliced green onion", new BigDecimal(2), tableSpoonUom));
        guacRecipe.addIngredient(new Ingredient("serrano chiles, stems and seeds removed, minced", new BigDecimal(2), eachUom));
        guacRecipe.addIngredient(new Ingredient("Cilantro", new BigDecimal(2), tableSpoonUom));
        guacRecipe.addIngredient(new Ingredient("freshly grated black pepper", new BigDecimal(2), dashUom));
        guacRecipe.addIngredient(new Ingredient("ripe tomato, seeds and pulp removed, chopped", new BigDecimal(".5"), eachUom));

        //add to return list
        guacRecipe.getCategories().add(getCategories().get(1));
        recipes.add(guacRecipe);

        //Recipe nr 2
        Recipe tacoRecipe = new Recipe();
        tacoRecipe.setDescription("Tacos");
        tacoRecipe.setTotalTime(30);
        tacoRecipe.setCalories(500);
        tacoRecipe.setDifficulty(Difficulty.MODERATE);
        tacoRecipe.setDirections("1 Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt. " +
                "\n" +
                "2 Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum. " +
                "\n" +
                "3 Ut enim ad minima veniam, quis nostrum exercitationem ullam corporis suscipit laboriosam, nisi ut aliquid ex ea commodi consequatur? .\n" +
                "4 Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, " +
                "eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo. Nemo enim ipsam " +
                "voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem " +
                "sequi nesciunt.\n" +
                "\n" +
                "\n" +
                "Read more: http://somewhereovertherainbow.pl");

        //very redundent - could add helper method, and make this simpler
        tacoRecipe.addIngredient(new Ingredient("taco", new BigDecimal(2), eachUom));
        tacoRecipe.addIngredient(new Ingredient("Salt", new BigDecimal(".5"), teapoonUom));
        tacoRecipe.addIngredient(new Ingredient("Lemon juice", new BigDecimal(2), tableSpoonUom));
        tacoRecipe.addIngredient(new Ingredient("Tomatos", new BigDecimal(2), tableSpoonUom));


        //add to return list
        tacoRecipe.getCategories().add(getCategories().get(1));
        recipes.add(tacoRecipe);

        //Recipe nr 3
        Recipe eggsRecipe = new Recipe();
        eggsRecipe.setDescription("Scrambled eggs");
        eggsRecipe.setTotalTime(10);
        eggsRecipe.setCalories(150);
        eggsRecipe.setDifficulty(Difficulty.EASY);
        eggsRecipe.setDirections("1 Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt. " +
                "\n" +
                "2 Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum. " +
                "\n" +
                "3 Ut enim ad minima veniam, quis nostrum exercitationem ullam corporis suscipit laboriosam, nisi ut aliquid ex ea commodi consequatur? .\n" +
                "4 Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, " +
                "eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo. Nemo enim ipsam " +
                "voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem " +
                "sequi nesciunt.\n" +
                "\n" +
                "\n" +
                "Read more: http://somewhereovertherainbow.pl");

        //very redundent - could add helper method, and make this simpler
        eggsRecipe.addIngredient(new Ingredient("taco", new BigDecimal(2), eachUom));
        eggsRecipe.addIngredient(new Ingredient("Salt", new BigDecimal(".1"), teapoonUom));
        eggsRecipe.addIngredient(new Ingredient("onion", new BigDecimal(1), pintUom));

        //add to return list
        eggsRecipe.getCategories().add(getCategories().get(0));
        recipes.add(eggsRecipe);

        //Recipe nr 3
        Recipe saladRecipe = new Recipe();
        saladRecipe.setDescription("Fruit salad");
        saladRecipe.setTotalTime(10);
        saladRecipe.setCalories(100);
        saladRecipe.setDifficulty(Difficulty.EASY);
        saladRecipe.setDirections("1 Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt. " +
                "\n" +
                "2 Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum. " +
                "\n" +
                "3 Ut enim ad minima veniam, quis nostrum exercitationem ullam corporis suscipit laboriosam, nisi ut aliquid ex ea commodi consequatur? .\n" +
                "4 Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, " +
                "eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo. Nemo enim ipsam " +
                "voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem " +
                "sequi nesciunt.\n" +
                "\n" +
                "\n" +
                "Read more: http://somewhereovertherainbow.pl");

        //very redundent - could add helper method, and make this simpler
        saladRecipe.addIngredient(new Ingredient("banan", new BigDecimal(1), eachUom));
        saladRecipe.addIngredient(new Ingredient("apple", new BigDecimal(".1"), eachUom));
        saladRecipe.addIngredient(new Ingredient("orange", new BigDecimal(1), eachUom));

        //add to return list
        saladRecipe.getCategories().add(getCategories().get(2));
        recipes.add(saladRecipe);


        return  recipes;
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

        categories.add(breakfastCategory);
        categories.add(dinnernCategory);
        categories.add(dessertCategory);
        return categories;
    }

}
