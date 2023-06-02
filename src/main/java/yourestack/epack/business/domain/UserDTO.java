package yourestack.epack.business.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

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
    @Pattern(regexp = "^[\\w!#$%&amp;'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&amp;'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$",
                        message = "email must contain @ sign, must not contain two dots in a row.")
    private String email;

    @NotBlank
    @Pattern(regexp = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%*]).{6,15})",
            message = "password must contain at least 1 uppercase, 1 lowercase, 1 special character and 1 digit ")
    private String password;

    @NotBlank
    private String passwordConfirmation;

    private String oldPassword;

    @Pattern(regexp = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%*]).{6,15})",
            message = "password must contain at least 1 uppercase, 1 lowercase, 1 special character and 1 digit ")

    private String newPassword;

    @DateTimeFormat(pattern="MM/dd/yyyy")
    private LocalDate dateOfBirth;

    @NotBlank
    @Size(max = 45)
    private String phoneNumber;

    @NotBlank
    @Size(max = 45)
    private String occupation;

    @DateTimeFormat(pattern="MM/dd/yyyy:HH:mm:ss")
    private LocalDateTime dateCreated;

    @DateTimeFormat(pattern="MM/dd/yyyy:HH:mm:ss")
    private LocalDateTime lastUpdated;

    public Set<RoleDTO> roleList;

    public Set<RoleDTO> getRoleList() {
        return roleList;
    }

    public List<OrderDTO> orderList;

    public List<OrderDTO> getOrderList() {
        return orderList;
    }

    public String getPassword() {
        return password;
    }

    public String getPasswordConfirmation() {
        return passwordConfirmation;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

}
