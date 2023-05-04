package impact.capstone.recipe.model.dto;

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
    private Long weather;
    private Long category;
}
