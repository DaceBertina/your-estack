package yourestack.epack.business.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class FeedbackJava extends FeedbackDTO{

    Long id;
    Long userId;
    String username;
    Long epackId;
    String feedText;
    LocalDateTime dateCreated;

    public FeedbackJava(Long id, Long userId, String username, Long epackId, String feedText, LocalDateTime dateCreated) {
        super(id, userId, username, epackId, feedText, dateCreated);
    }

    public String getDateCreated(LocalDateTime dateCreated) {
        String date = String.valueOf(dateCreated);
        return date.substring(0, 10);
    }
}
