package impact.capstone.recipe.repository;

import impact.capstone.recipe.model.entity.RecipeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeDetailRepository extends JpaRepository<RecipeEntity, Long> {
    RecipeEntity findByRecipeNum(Long recipeNum);
}
