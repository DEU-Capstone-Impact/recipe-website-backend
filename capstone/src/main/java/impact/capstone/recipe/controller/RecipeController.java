package impact.capstone.recipe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import impact.capstone.recipe.model.dto.RecipeDTO;
import impact.capstone.recipe.service.RecipeService;

@Controller
public class RecipeController {

    private final RecipeService recipeService;

    @Autowired
    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("/recipes/new")
    public ModelAndView showRecipeForm() {
        ModelAndView modelAndView = new ModelAndView("recipe-form");
        modelAndView.addObject("recipe", new RecipeDTO());
        return modelAndView;
    }

    @PostMapping("/recipes")
    public String createRecipe(@ModelAttribute("recipe") RecipeDTO recipe) {
        recipeService.createRecipe(recipe);
        return "redirect:/recipes";
    }
}
