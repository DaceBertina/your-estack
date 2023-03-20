package yourestack.epack.business.model.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import yourestack.epack.business.model.CategoryEntity;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
}
