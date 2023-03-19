package yourestack.epack.business.domain;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class WorkplaceDTO {

    private Long workplaceId;

    @NotNull
    @Size(max = 45)
    private String companyName;

    @NotNull
    @Size(max = 45)
    private String position;

    @Size(max = 45)
    private String companyPhoneNumber;

    private Long client;

}
