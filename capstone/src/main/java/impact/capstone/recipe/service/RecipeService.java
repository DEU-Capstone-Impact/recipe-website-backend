package impact.capstone.recipe.service;

import impact.capstone.recipe.Enum.WeatherEnum;
import impact.capstone.recipe.model.dto.RecipeDTO;
import impact.capstone.recipe.model.entity.RecipeEntity;
import impact.capstone.recipe.repository.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecipeService {
    private final RecipeSortByViewRepository recipeSortByViewRepository;
    private final RecipeCategoryRepository recipeSearchRepository;
    private final RecipeIngredientsRepository recipeIngredientsRepository;

    private final RecipeWeatherRepository recipeWeatherRepository;
    private final RecipeDetailRepository recipeDetailRepository;

    public RecipeService(RecipeSortByViewRepository recipeRepository, RecipeCategoryRepository recipeSearchRepository, RecipeIngredientsRepository recipeKeywordRepository, RecipeWeatherRepository recipeWeatherRepository, RecipeDetailRepository recipeDetailRepository) {
        this.recipeSortByViewRepository = recipeRepository;
        this.recipeSearchRepository = recipeSearchRepository;
        this.recipeIngredientsRepository = recipeKeywordRepository;
        this.recipeWeatherRepository = recipeWeatherRepository;
        this.recipeDetailRepository = recipeDetailRepository;
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

    public List<RecipeDTO> weatherRecipe(WeatherEnum weather) {
        List<RecipeDTO> recipeDTOList = recipeWeatherRepository.findAllByWeather(weather);
        return recipeDTOList;
    }

    public RecipeDTO getRecipeDetails(Long recipeNum) {
        RecipeEntity recipeEntity = recipeDetailRepository.findByRecipeNum(recipeNum);
        // 레시피 엔티티를 DTO로 변환하는 로직
        RecipeDTO recipeDTO = convertToDTO(recipeEntity);
        return recipeDTO;
    }

    private RecipeDTO convertToDTO(RecipeEntity recipeEntity) {
        // 엔티티를 DTO로 변환하는 로직
        RecipeDTO recipeDTO = new RecipeDTO();
        recipeDTO.setRecipeNum(recipeEntity.getRecipeNum());
        recipeDTO.setTitle(recipeEntity.getTitle());
        // 나머지 필드 설정
        return recipeDTO;
    }


    public List<RecipeDTO> sortByViewRecipe() {
        List<RecipeDTO> recipeDTOList = recipeSortByViewRepository.findAllByOrderByViewDesc();
        return recipeDTOList;
    }
}
