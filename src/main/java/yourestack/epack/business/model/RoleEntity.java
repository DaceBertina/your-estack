package yourestack.epack.business.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "roles")
@NoArgsConstructor
@AllArgsConstructor
public class RoleEntity {

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="role_id")
    private Long roleId;

    //@Enumerated(EnumType.STRING)
    @Column(name="role_name", length = 20)
    private String roleName;

//    public RoleEntity(RoleEnum name) {
//        this.roleName = name;
//    }

    @Column(name="description")
    private String description;

}
