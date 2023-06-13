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
    private String firstName;

    @NotBlank
    private String lastName;

    @Size(max = 3)
    private String gender;

    @NotBlank
    @Size(max = 20)
    private String username;

    @NotBlank
    @Size(max = 60)
    @Pattern(regexp = "^[\\w!#$%&amp;'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&amp;'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$",
                        message="Email has not a proper format or contains illegal characters")
    private String email;

    @NotBlank
    @Pattern(regexp = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%*]).{8,15})",
                        message="{password.pattern}")
    private String password;

    @NotBlank
    private String passwordConfirmation;

    private String oldPassword;

    @Pattern(regexp = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%*]).{8,15})",
                    message="Password must contain at least 1 uppercase, 1 lowercase, 1 special character and 1 digit, length: 8-15")

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
