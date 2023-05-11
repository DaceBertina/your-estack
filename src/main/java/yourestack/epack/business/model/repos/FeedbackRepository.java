package yourestack.epack.business.model.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import yourestack.epack.business.model.EpackEntity;
import yourestack.epack.business.model.FeedbackEntity;
import yourestack.epack.business.model.UserEntity;

import java.util.List;

@Repository
public interface FeedbackRepository extends JpaRepository<FeedbackEntity, Long> {

    List<FeedbackEntity> findAllByUserEntity(UserEntity userEntity);

}
