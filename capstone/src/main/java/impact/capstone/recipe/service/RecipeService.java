package impact.capstone.recipe.service;

import impact.capstone.recipe.model.dto.RecipeDTO;
import impact.capstone.recipe.model.entity.RecipeEntity;
import impact.capstone.recipe.repository.RecipeSortByViewRepository;
import impact.capstone.recipe.repository.RecipeCategorySearchRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecipeService {
    private final RecipeSortByViewRepository recipeSortByViewRepository;
    private final RecipeCategorySearchRepository recipeSearchRepository;

    public RecipeService(RecipeSortByViewRepository recipeRepository, RecipeCategorySearchRepository recipeSearchRepository) {
        this.recipeSortByViewRepository = recipeRepository;
        this.recipeSearchRepository = recipeSearchRepository;
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

    public List<RecipeDTO> searchRecipe(String keyword) {
        List<RecipeDTO> recipeDTOList = recipeSearchRepository.findByCategoryContaining(keyword);
        return recipeDTOList;
    }

    public List<RecipeDTO> sortByViewRecipe() {
        List<RecipeDTO> recipeDTOList = recipeSortByViewRepository.findByViewDesc();
        return recipeDTOList;
    }
}
