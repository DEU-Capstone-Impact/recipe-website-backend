package impact.capstone.recipe.repository;

import impact.capstone.recipe.Enum.CategoryEnum;
import impact.capstone.recipe.model.dto.RecipeDTO;
import impact.capstone.recipe.model.entity.RecipeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecipeCategoryRepository extends JpaRepository<RecipeEntity, String> {
    List<RecipeDTO> findByCategoryContaining(CategoryEnum category);
}
