package yourestack.epack.business.service;
//https://www.baeldung.com/registration-with-spring-mvc-and-spring-security
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import yourestack.epack.business.domain.UserDTO;
import yourestack.epack.business.domain.UserDetailsImpl;
import yourestack.epack.business.mappers.UserMapStruct;
import yourestack.epack.business.model.RoleEntity;
import yourestack.epack.business.model.UserEntity;
import yourestack.epack.business.model.repos.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@Transactional
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    private final UserMapStruct userMapper;


    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with email: " + email));
        if (user == null) {
            throw new UsernameNotFoundException("No user found with email: " + email);
        }
//        UserDTO userDTO = userMapper.userEntityToUserDto(user);
        boolean enabled = true;
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;

//        return UserDetailsImpl.build(userDTO);

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(), user.getPassword(), enabled, accountNonExpired,
                credentialsNonExpired, accountNonLocked, getAuthorities(user.getRoleEntityList()));
    }

    private static List<GrantedAuthority> getAuthorities (Set<RoleEntity> roleEntityList) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (RoleEntity role : roleEntityList) {
            authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
        }
        return authorities;
    }
}
