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
@Table(name = "users",
        uniqueConstraints = {
        @UniqueConstraint(columnNames = "username"),
        @UniqueConstraint(columnNames = "email")
})
public class UserEntity {

    @Id
    @Column(name = "user_id", nullable = false, updatable = false)
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

    @OneToMany(mappedBy = "userEntity")
    @Transient
    private List<AddressEntity> userAddress;

    @OneToMany(mappedBy = "userEntity")
    @Transient
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

    @Transient
    private List<OrderEntity> orderEntityList;

    public List<OrderEntity> getOrders() {
        return orderEntityList;
    }

    public UserEntity(Long userId) {
        this.userId = userId;
    }
}
