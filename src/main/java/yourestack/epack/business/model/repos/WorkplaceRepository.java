package yourestack.epack.business.model.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import yourestack.epack.business.model.WorkplaceEntity;

@Repository
public interface WorkplaceRepository extends JpaRepository<WorkplaceEntity, Long> {
}
