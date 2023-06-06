package impact.capstone.recipe.repository;

import impact.capstone.recipe.model.dto.RecipeDTO;
import impact.capstone.recipe.model.entity.RecipeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecipeCategorySearchRepository extends JpaRepository<RecipeEntity, String> {
    List<RecipeDTO> findByTitleContaining(String keyword);

}
