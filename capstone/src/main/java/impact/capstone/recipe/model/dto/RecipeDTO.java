package impact.capstone.recipe.model.dto;

import impact.capstone.recipe.Enum.CategoryEnum;
import impact.capstone.recipe.Enum.WeatherEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RecipeDTO {
    private Long recipeNum;
    private String title;
    private Long view;
    private String recipeContent;
    private String ingredient;
    private WeatherEnum weather;
    private CategoryEnum category;

    private String recipe_photo;
}
