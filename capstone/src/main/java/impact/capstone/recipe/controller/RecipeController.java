package impact.capstone.recipe.controller;

import impact.capstone.recipe.Enum.CategoryEnum;
import impact.capstone.recipe.Enum.WeatherEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
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
    public String showRecipeCreateForm(Model model) {
        model.addAttribute("recipe", new RecipeDTO());
        return "recipe-create-form";
    }

    @PostMapping("/recipe") // 레시피 업로드 (2023. 06. 07 수정)
    public String createRecipe(@ModelAttribute("recipe") RecipeDTO recipe, @RequestParam("photoFile") MultipartFile photoFile) {
        recipeService.createRecipe(recipe, photoFile);
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

    @PostMapping("/recipe/weather")
    public ModelAndView showRecipeWeatherForm(@RequestParam WeatherEnum weather) {
        List<RecipeDTO> recipeDTOList = recipeService.weatherRecipe(weather);
        ModelAndView modelAndView = new ModelAndView("recipe-weather-result");
        modelAndView.addObject("searchedWeatherRecipes", recipeDTOList);
        return modelAndView;
    }

    @GetMapping("/recipe/boards")
    public String getRecipeBoardsByViewCount(Model model) {
        List<RecipeDTO> recipeDTOList = recipeService.sortByViewRecipe();
        model.addAttribute("recipeBoards", recipeDTOList);
        return "recipe-boards";
    }

    @GetMapping("/recipes/{recipeNum}")
    public ModelAndView showRecipeDetails(@PathVariable Long recipeNum) {
        RecipeDTO recipeDTO = recipeService.getRecipeDetails(recipeNum);
        ModelAndView modelAndView = new ModelAndView("recipe-details");
        modelAndView.addObject("recipe", recipeDTO);
        return modelAndView;
    }


}
