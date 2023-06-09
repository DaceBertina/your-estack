package yourestack.epack.business.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;


@Entity
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "epacks")
public class EpackEntity {

    @Id
    @Column(name = "epack_id", nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long epackId;

    @Column(name = "epack_name", nullable = false, unique = true)
    private String epackName;

    @Column(name = "description")
    private String description;

    @Column(name = "manager_name", nullable = false)
    private String manager;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private CategoryEntity categoryEntity;

    @Column(name = "duration_h", nullable = false)
    private Integer duration;

    @CreatedDate
    @Column(name = "date_created", nullable = false, updatable = false)
    private LocalDateTime dateTimeCreated;

    @LastModifiedDate
    @Column(name = "last_updated", nullable = false)
    private LocalDateTime lastUpdated;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive;

    @Column(name = "price", nullable = false)
    private Double price;

    @Column(name = "urllink")
    private String urllink;

    @OneToMany(mappedBy="epackEntity")
    @Transient
    private List<OrderEntity> orderEntityList;

    @OneToMany(mappedBy="epackEntity")
    @Transient
    private List<FeedbackEntity> feedbackEntityList;

    public EpackEntity(Long epackId) {
        this.epackId = epackId;
    }

}
