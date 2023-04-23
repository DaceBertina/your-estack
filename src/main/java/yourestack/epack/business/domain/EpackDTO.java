package yourestack.epack.business.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import yourestack.epack.business.model.OrderEntity;

import java.time.OffsetDateTime;
import java.util.List;


@Getter
@Setter
public class EpackDTO {

    private Long epackId;

    @NotNull
    @Size(max = 255)
    private String epackName;

    @NotBlank
    private String description;

    @NotBlank
    private String manager;

    @NotBlank
    private Long categoryId;

    @NotBlank
    private Integer duration;

    private OffsetDateTime dateCreated;

    private OffsetDateTime lastUpdated;

    private Boolean isActive;

    private Double price;

    private String urllink;

    public List<OrderDTO> ordersList;

}
