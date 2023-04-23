package yourestack.epack.business.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

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

//    @Column(name = "user_email")
//    private String userEmail;

    @Column(name = "epack_price")
    Double epackPrice;

    @ManyToOne(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
    @JoinColumn(name = "email", insertable=false, updatable=false)
    private UserEntity userEntity;

    @ManyToOne(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
    @JoinColumn(name = "epack_id", foreignKey = @ForeignKey(name = "FK_epack_id"), insertable = false, updatable = false)
    private EpackEntity epackEntity;

    public EpackEntity getEpackEntity(Integer epackId) {
        return epackEntity;
    }

    public void setEpackEntity(EpackEntity epackEntity) {
        this.epackEntity = epackEntity;
    }

}

