package yourestack.epack.business.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FeedbackDTO {

    Long id;
    Long userId;
    String username;
    Long epackId;
    String feedText;
    LocalDateTime dateCreated;

    public String getDateCreated(LocalDateTime dateCreated) {
        String date = String.valueOf(dateCreated);
        return date.substring(0, 10);
    }
}
