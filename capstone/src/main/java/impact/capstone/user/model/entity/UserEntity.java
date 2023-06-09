package impact.capstone.user.model.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Table(name = "user")
public class UserEntity {
    @Id
    private Long id;
    private String username;
    private String password;
    private Long point;
}
