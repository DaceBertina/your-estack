package yourestack.epack.business.model.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import yourestack.epack.business.model.EpackEntity;

@Repository
public interface EpackRepository extends JpaRepository<EpackEntity, Long> {

    boolean existsByEpackNameIgnoreCase(String epackName);

}
