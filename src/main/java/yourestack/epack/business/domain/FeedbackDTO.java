package yourestack.epack.business.domain;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class FeedbackDTO {

    Long id;
    Long userId;
    Long epackId;
    String feedText;
    LocalDateTime dateCreated;
}
