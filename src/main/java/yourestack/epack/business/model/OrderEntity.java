package yourestack.epack.business.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
//@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Transactional
@Table(name = "orders")
public class OrderEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id", nullable = false, unique = true)
    Long orderId;

    @Column(name = "epack_price")
    Double epackPrice;

//    @Column(name = "user_email")
//    String userEmail;

    @ManyToOne(fetch = FetchType.LAZY, cascade=CascadeType.MERGE)
    @JoinColumn(name = "email", foreignKey = @ForeignKey(name = "FK_email"), insertable = false, updatable = false)
    private UserEntity userEntity;

    public String getEmail(UserEntity userEntity) {
        return this.userEntity.getEmail();
    }
    @ManyToOne(fetch = FetchType.LAZY, cascade=CascadeType.MERGE)
    @JoinColumn(name = "epack_id", foreignKey = @ForeignKey(name = "FK_epack_id"), insertable = false, updatable = false)
    private EpackEntity epackEntity;

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

