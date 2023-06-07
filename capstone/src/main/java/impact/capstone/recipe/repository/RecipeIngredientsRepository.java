package impact.capstone.recipe.repository;

import impact.capstone.recipe.model.dto.RecipeDTO;

import java.util.List;

public interface RecipeIngredientsRepository {
    List<RecipeDTO> findByIngredientsContaining(String ingredients);
}
