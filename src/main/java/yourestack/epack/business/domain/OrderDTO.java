package yourestack.epack.business.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter

public class OrderDTO {

    Long orderId;
    String userEmail;
    Long epackId;
    Double epackPrice;
    EpackDTO epackDTO;
    UserDTO user;

    LocalDateTime dateCreated;

    public String getEmail(UserDTO user) {
        return user.getEmail();
    }

}
