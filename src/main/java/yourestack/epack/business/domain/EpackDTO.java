package yourestack.epack.business.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;


@Getter
@Setter
public class EpackDTO {

    public Long epackId;

    @NotNull
    @Size(max = 255)
    public String epackName;

    @NotBlank
    public String description;

    @NotBlank
    public String manager;

    @NotBlank
    public Long categoryId;

    @NotBlank
    public Integer duration;

    public LocalDateTime dateCreated;

    public LocalDateTime lastUpdated;

    public Boolean isActive;

    public Double price;

    public String urllink;

    public List<OrderDTO> ordersList;

    public String getDateCreated(LocalDateTime dateCreated) {
        return String.valueOf(dateCreated);
    }

    public EpackDTO(Long epackId) {
        this.epackId = epackId;
    }
}
