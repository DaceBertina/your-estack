package yourestack.epack.business.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Data
public class UserDTO {

    private Long userId;

    @NotBlank
    @Size(max = 45)
    private String firstName;

    @NotBlank
    @Size(max = 45, message = "Last name should be at least 2 char long.")
    private String lastName;

    @Size(max = 10)
    private String gender;

    @NotBlank
    @Size(max = 20)
    private String username;

    @NotBlank
    @Size(max = 60)
    private String email;

    @NotBlank
    private String password;

    private LocalDate dateOfBirth;

    @NotBlank
    @Size(max = 45)
    private String phoneNumber;

    @NotBlank
    @Size(max = 45)
    private String occupation;

    private LocalDateTime dateCreated;

    private LocalDateTime lastUpdated;

    public Set<RoleDTO> roleList;

    public Set<RoleDTO> getRoleList() {
        return roleList;
    }

    public List<OrderDTO> orderList;

    public List<OrderDTO> getOrderList() {
        return orderList;
    }

}
