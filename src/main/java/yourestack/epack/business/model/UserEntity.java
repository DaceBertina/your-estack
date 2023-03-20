package yourestack.epack.business.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import yourestack.epack.business.domain.RoleEnum;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
//        , uniqueConstraints = {
//        @UniqueConstraint(columnNames = "username"),
//        @UniqueConstraint(columnNames = "email")
//})
public class UserEntity {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(nullable = false, length = 45)
    private String firstName;

    @Column(nullable = false, length = 45)
    private String lastName;

    @Column
    private String gender;

    @NotBlank
    @Size(max = 20)
    @Column(nullable = false)
    private String username;

    @NotBlank
    @Size(max = 50)
    @Email
    @Column(nullable = false)
    private String email;

    @NotBlank
    @Size(max = 150)
    @Column(nullable = false)
    private String password;

    @Column
    private LocalDate dateOfBirth;

    @Column(nullable = false, length = 45)
    private String phoneNumber;

    @Column(nullable = false, length = 45)
    private String occupation;

    @OneToMany(mappedBy = "user")
    private List<AddressEntity> userAddress;

    @OneToMany(mappedBy = "user")
    private List<WorkplaceEntity> userWorkplaces;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime dateCreated;

    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime lastUpdated;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(
            name = "users_roles",
            joinColumns = { @JoinColumn(
                    name = "user_email", referencedColumnName = "email")},
            inverseJoinColumns = { @JoinColumn(
                    name = "role_id", referencedColumnName = "role_id") } )

    private Set<RoleEntity> roleEntityList;


//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return List.of(new SimpleGrantedAuthority(role.name()));
//    }

//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>(roleEntitySet.size());
//
//        for (RoleEntity role : roleEntitySet)
//            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName()));
//
//        return authorities;
//    }
//
//    @Override
//    public String getPassword() {
//        return password;
//    }
//
//    @Override
//    public String getUsername() {
//        return email;
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
//
//    public Object getHash() {
//        return null;
//    }
}
