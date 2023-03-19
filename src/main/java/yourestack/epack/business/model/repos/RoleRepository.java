package yourestack.epack.business.model.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import yourestack.epack.business.model.RoleEntity;

import java.util.Optional;


@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, String> {

    @Query(value = "SELECT role FROM RoleEntity role WHERE role.roleName = ?1")
    Optional<RoleEntity> findByName(String name);
}
