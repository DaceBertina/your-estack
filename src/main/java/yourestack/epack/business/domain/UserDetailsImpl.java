package yourestack.epack.business.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serial;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class UserDetailsImpl implements UserDetails {

    private  UserDTO userDTO;

    @Serial
    private static final long serialVersionUID = -3213430314663136523L;
//
//    private final Long id;
//
//    private final String username;
//
//    private final String email;
//
//    @JsonIgnore
//    private final String password;

//    private final Collection<? extends GrantedAuthority> authorities;
//    public UserDetailsImpl(Long id, String username, String email, String password,
//                           Collection<? extends GrantedAuthority> authorities, UserDTO user) {
//        this.id = id;
//        this.username = username;
//        this.email = email;
//        this.password = password;
//        this.authorities = authorities;
//    }

    public UserDetailsImpl(UserDTO user) {
        this.userDTO = user;
    }

//    public static UserDetailsImpl build(UserDTO user) {
//        List<GrantedAuthority> authorities = user.getRoleList().stream()
//                .map(role -> new SimpleGrantedAuthority(role.getRoleName()))
//                .collect(Collectors.toList());
//
//        return new UserDetailsImpl(
//                user.getUserId(),
//                user.getUsername(),
//                user.getEmail(),
//                user.getPassword(),
//                authorities);
//    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return userDTO.getRoleList().stream()
              .map(role -> new SimpleGrantedAuthority(role.getRoleName()))
                .collect(Collectors.toList());
    }

    public Long getId() {
        return userDTO.getUserId();
    }

    public String getEmail() {
        return userDTO.getEmail();
    }

    @Override
    public String getPassword() {
        return userDTO.getPassword();
    }

    @Override
    public String getUsername() {
        return userDTO.getUsername();
    }

    public String getFirstName() {
        return userDTO.getFirstName();
    }

    public String getLastName() {
        return userDTO.getLastName();
    }

    public String getGender() {
        return userDTO.getGender();
    }

    public String getPhoneNumber() {
        return userDTO.getPhoneNumber();
    }

    public LocalDate getDateOfBirth() {
        return userDTO.getDateOfBirth();
    }

    public String getOccupation() {
        return userDTO.getOccupation();
    }

    public List<OrderDTO> getOrders() { return userDTO.getOrders();}

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o)
//            return true;
//        if (o == null || getClass() != o.getClass())
//            return false;
//        UserDetailsImpl user = (UserDetailsImpl) o;
//        return Objects.equals(id, user.id);
//    }
}

