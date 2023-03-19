package yourestack.epack.business.model.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import yourestack.epack.business.model.UserEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByEmail(String email);

    List<UserEntity> findAll();

    @Query(value = "SELECT u FROM UserEntity u WHERE u.username = ?1")
    Optional<UserEntity> findByUsername(@Param("username") String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
}
