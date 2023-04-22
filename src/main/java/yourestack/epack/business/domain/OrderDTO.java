package yourestack.epack.business.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDTO {

    Long orderId;
    String userEmail;
    Double coursePrice;

}
