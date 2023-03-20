package yourestack.epack.business.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Data
public class UserDTO {

    private Long userId;

    @NotBlank
    @Size(max = 45)
    private String firstName;

    @NotBlank
    @Size(max = 45)
    private String lastName;

    @Size(max = 2)
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

    //    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
//    }

//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>(roleSet.size());
//
//         for (RoleDTO role : roleSet)
//            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName()));
//
//        return authorities;
//    }
//
//    @Override
//    public String getUsername() {
//        return this.email;
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return true;
//    }
}
