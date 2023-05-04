package impact.capstone.user.repository;

import impact.capstone.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    @Query("select (count(u) > 0) from UserEntity u where u.userId = ?1")
    boolean existsByUserId(String userId);
}
