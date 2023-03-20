package yourestack.epack.business.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.OffsetDateTime;
import java.util.Set;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@Table(name = "categories")
public class CategoryEntity {

    @Id
    @Column(name = "category_id", nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryId;

    @Column(name = "category_name", nullable = false, unique = true)
    private String name;

    @OneToMany(mappedBy = "categoryEntity")
    private Set<EpackEntity> categoryEpacks;

    @CreatedDate
    @Column(name = "date_created", nullable = false, updatable = false)
    private OffsetDateTime dateCreated;

    @LastModifiedDate
    @Column(name = "last_updated", nullable = false)
    private OffsetDateTime lastUpdated;

    public CategoryEntity(Long id) {
        this.categoryId = id;
    }
}
