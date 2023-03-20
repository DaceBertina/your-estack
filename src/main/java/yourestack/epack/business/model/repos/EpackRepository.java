package yourestack.epack.business.model.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import yourestack.epack.business.model.EpackEntity;


public interface EpackRepository extends JpaRepository<EpackEntity, Long> {

    boolean existsByEpackNameIgnoreCase(String epackName);

}
