package impact.capstone.recipe.repository;

import impact.capstone.recipe.Enum.WeatherEnum;
import impact.capstone.recipe.model.dto.RecipeDTO;
import impact.capstone.recipe.model.entity.RecipeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecipeWeatherRepository extends JpaRepository<RecipeEntity, String> {
    List<RecipeDTO> findAllByWeather(WeatherEnum weather);
}
