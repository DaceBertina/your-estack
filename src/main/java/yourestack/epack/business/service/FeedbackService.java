package yourestack.epack.business.service;

import yourestack.epack.business.domain.FeedbackDTO;
import yourestack.epack.business.domain.UserDTO;

import java.util.List;

public interface FeedbackService {

    public List<FeedbackDTO> findAllByUserId(Long userId);

    public List<FeedbackDTO> findAll();

    public List<FeedbackDTO> findAllJavaFeedbacks();

    public List<FeedbackDTO> findAllSpringFeedbacks();

    public List<FeedbackDTO> findAllMicroFeedbacks();

    void saveFeedback(FeedbackDTO feedback, UserDTO user);

}
