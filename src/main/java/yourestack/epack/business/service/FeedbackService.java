package yourestack.epack.business.service;

import yourestack.epack.business.domain.FeedbackDTO;
import yourestack.epack.business.domain.UserDTO;

import java.util.List;

public interface FeedbackService {

    public List<FeedbackDTO> findAllByUserEntity(Long userId);

    public List<FeedbackDTO> findAll();

    void saveFeedback(FeedbackDTO feedback, UserDTO user);

}
