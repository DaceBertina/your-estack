package yourestack.epack.business.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDTO {

    Long orderId;
    String userEmail;
    Long epackId;
    Double epackPrice;
    EpackDTO epack;
    public EpackDTO getEpack(Long epackId) {
        return epack;
    }
}
