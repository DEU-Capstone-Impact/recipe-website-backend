package impact.capstone.recipe.model.entity;

import impact.capstone.recipe.Enum.CategoryEnum;
import impact.capstone.recipe.Enum.WeatherEnum;
import lombok.*;

import javax.persistence.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "recipe")
public class RecipeEntity {
    @Id
    @Column(name="number")
    private Long recipeNum;
    private String title;
    private Long view;
    @Column(name="recipe")
    private String recipeContent;
    private String ingredient;
    @Enumerated(EnumType.STRING)
    private WeatherEnum weather;
    @Enumerated(EnumType.STRING)
    @Column(name="cate")
    private CategoryEnum category;
    private String recipe_photo;
}
