package impact.capstone.recipe.service;

import impact.capstone.recipe.model.dto.RecipeDTO;
import impact.capstone.recipe.model.entity.RecipeEntity;
import impact.capstone.recipe.repository.RecipeIngredientsRepository;
import impact.capstone.recipe.repository.RecipeSortByViewRepository;
import impact.capstone.recipe.repository.RecipeCategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecipeService {
    private final RecipeSortByViewRepository recipeSortByViewRepository;
    private final RecipeCategoryRepository recipeSearchRepository;
    private final RecipeIngredientsRepository recipeIngredientsRepository;

    public RecipeService(RecipeSortByViewRepository recipeRepository, RecipeCategoryRepository recipeSearchRepository, RecipeIngredientsRepository recipeKeywordRepository) {
        this.recipeSortByViewRepository = recipeRepository;
        this.recipeSearchRepository = recipeSearchRepository;
        this.recipeIngredientsRepository = recipeKeywordRepository;
    }

    public RecipeEntity createRecipe(RecipeDTO recipeDTO) {
        // RecipeDTO를 Recipe 엔티티로 변환하여 저장
        RecipeEntity recipe = RecipeEntity.builder()
                .title(recipeDTO.getTitle())
                .view(recipeDTO.getView())
                .recipeContent(recipeDTO.getRecipeContent())
                .ingredient(recipeDTO.getIngredient())
                .weather(recipeDTO.getWeather())
                .category(recipeDTO.getCategory())
                .build();

        return recipeSortByViewRepository.save(recipe);
    }

    public List<RecipeDTO> categoryRecipe(String category) {
        List<RecipeDTO> recipeDTOList = recipeSearchRepository.findByCategoryContaining(category);
        return recipeDTOList;
    }

    public List<RecipeDTO> ingredientsRecipe(String ingredients) {
        List<RecipeDTO> recipeDTOList = recipeIngredientsRepository.findByIngredientContaining(ingredients);
        return recipeDTOList;
    }

    public List<RecipeDTO> sortByViewRecipe() {
        List<RecipeDTO> recipeDTOList = recipeSortByViewRepository.findByViewDesc();
        return recipeDTOList;
    }
}
