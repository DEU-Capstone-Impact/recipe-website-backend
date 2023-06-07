package impact.capstone.recipe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

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
    public ModelAndView showRecipeCreateForm() {
        ModelAndView modelAndView = new ModelAndView("recipe-create-form");
        modelAndView.addObject("recipe", new RecipeDTO());
        return modelAndView;
    }

    @PostMapping("/recipe")
    public String createRecipe(@ModelAttribute("recipe") RecipeDTO recipe) {
        recipeService.createRecipe(recipe);
        return "redirect:/recipe";
    }

    @PostMapping("/recipe/search")
    public ModelAndView showRecipeSearchForm(@RequestParam String keyword) {
        List<RecipeDTO> recipeDTOList = recipeService.searchRecipe(keyword);
        ModelAndView modelAndView = new ModelAndView("recipe-category-search-result");
        modelAndView.addObject("searchedRecipes", recipeDTOList);
        return modelAndView;
    }

    @GetMapping("/recipe/boards")
    public String getRecipeBoardsByViewCount(Model model) {
        List<RecipeDTO> recipeDTOList = recipeService.sortByViewRecipe();
        model.addAttribute("recipeBoards", recipeDTOList);
        return "recipe-boards";
    }

}
