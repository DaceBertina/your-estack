package yourestack.epack.business.model.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import yourestack.epack.business.model.OrderEntity;

import java.util.List;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

    @Override
    List<OrderEntity> findAll();

}
