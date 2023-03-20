package yourestack.epack.business.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;


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

    private OffsetDateTime dateCreated;

    private OffsetDateTime lastUpdated;

}
