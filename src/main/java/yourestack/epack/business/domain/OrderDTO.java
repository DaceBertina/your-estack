package yourestack.epack.business.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class OrderDTO {

    Long orderId;
    Long userId;
    Long epackId;
    Double epackPrice;
    EpackDTO epackDTO;
    UserDTO user;

    LocalDateTime dateCreated;

    public String getUserEmail(UserDTO user) {
        return user.getEmail();
    }

    public EpackDTO getEpack() {
        return epackDTO;
    }
}
