package impact.capstone.recipe.model.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Table(name = "recipe")
public class RecipeEntity {
    @Id
    private Long recipeNum;
    private String title;
    private Long view;
    private String recipeContent;
    private String ingredient;
    @Enumerated(EnumType.STRING)
    private Long weather;
    @Enumerated(EnumType.STRING)
    private Long category;
}