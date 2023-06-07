package impact.capstone.recipe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import impact.capstone.recipe.model.dto.RecipeDTO;
import impact.capstone.recipe.service.RecipeService;

import java.util.List;

@Controller
public class RecipeController {

    private final RecipeService recipeService;

    @Autowired
    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("/recipe/upload")
    public String showRecipeCreateForm(Model model) {
        model.addAttribute("recipe", new RecipeDTO());
        return "recipe-create-form";
    }

    @PostMapping("/recipe")
    public String createRecipe(@ModelAttribute("recipe") RecipeDTO recipe) {
        recipeService.createRecipe(recipe);
        return "redirect:/recipe";
    }

    @PostMapping("/recipe/keyword")
    public String showRecipeKeywordForm(@RequestParam String ingredients, Model model) {
        List<RecipeDTO> recipeDTOList = recipeService.ingredientsRecipe(ingredients);
        model.addAttribute("searchedKeywordRecipes", recipeDTOList);
        return "recipe-keyword-result";
    }

    @PostMapping("/recipe/category")
    public String showRecipeCategoryForm(@RequestParam String category, Model model) {
        List<RecipeDTO> recipeDTOList = recipeService.categoryRecipe(category);
        model.addAttribute("searchedCategoryRecipes", recipeDTOList);
        return "recipe-category-result";
    }

    @GetMapping("/recipe/boards")
    public String getRecipeBoardsByViewCount(Model model) {
        List<RecipeDTO> recipeDTOList = recipeService.sortByViewRecipe();
        model.addAttribute("recipeBoards", recipeDTOList);
        return "recipe-boards";
    }

}
