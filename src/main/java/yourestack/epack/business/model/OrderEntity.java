package yourestack.epack.business.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import java.time.LocalDateTime;

@Entity
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "orders")
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id", nullable = false, unique = true)
    Long orderId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    @ManyToOne
    @JoinColumn(name = "epack_id")
    private EpackEntity epackEntity;

    @Column(name = "epack_price")
    Double epackPrice;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime dateCreated;

    public EpackEntity getEpackEntity(Integer epackId) {
        return epackEntity;
    }

    public void setEpackEntity(EpackEntity epackEntity) {
        this.epackEntity = epackEntity;
    }

}

