package yourestack.epack.business.domain;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serial;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class UserDetailsImpl implements UserDetails {

    private  UserDTO userDTO;

    @Serial
    private static final long serialVersionUID = -3213430314663136523L;


    public UserDetailsImpl(UserDTO user) {
        this.userDTO = user;
    }

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

