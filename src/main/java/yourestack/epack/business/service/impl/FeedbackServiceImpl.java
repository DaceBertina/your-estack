package yourestack.epack.business.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import yourestack.epack.business.domain.FeedbackDTO;
import yourestack.epack.business.domain.UserDTO;
import yourestack.epack.business.mappers.FeedbackMapStruct;
import yourestack.epack.business.model.FeedbackEntity;
import yourestack.epack.business.model.UserEntity;
import yourestack.epack.business.model.repos.FeedbackRepository;
import yourestack.epack.business.model.repos.UserRepository;
import yourestack.epack.business.service.FeedbackService;
import yourestack.epack.util.NotFoundException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@Service
@RequiredArgsConstructor
public class FeedbackServiceImpl implements FeedbackService {

    private final FeedbackRepository feedbackRepository;

    private final FeedbackMapStruct feedbackMapper;

    private final UserRepository userRepository;

    @Override
    public List<FeedbackDTO> findAllByUserEntity(Long userId) {
        final UserEntity userEntity = userRepository.findById(userId)
                .orElseThrow(NotFoundException::new);

        return feedbackRepository
                .findAllByUserEntity(userEntity)
                .stream()
                .map(feedbackMapper::feedbackEntityToFeedbackDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<FeedbackDTO> findAll() {
        final List<FeedbackEntity> feedbacks = feedbackRepository.findAll();
        return feedbacks.stream()
                .map(feedbackMapper::feedbackEntityToFeedbackDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<FeedbackDTO> findAllJavaFeedbacks() {
        final List<FeedbackEntity> feedbacks = feedbackRepository.findAll();
        return feedbacks.stream()
                .filter(feedback -> feedback.getEpackEntity().getEpackId().equals(1L))
                .map(feedbackMapper::feedbackEntityToFeedbackDto)
                .collect(Collectors.toList());
    }

    @Override
    public void saveFeedback(FeedbackDTO feedback, UserDTO user) {
        feedback.setUserId(user.getUserId());
        feedback.setEpackId(feedback.getEpackId());
        feedback.setFeedText(feedback.getFeedText());
        feedback.setDateCreated(LocalDateTime.now());
        FeedbackEntity feedbackEntity = feedbackRepository.save(feedbackMapper.feedbackDtoToFeedbackEntity(feedback));
        log.info("New feedback added: {}", feedbackEntity);

    }

}
