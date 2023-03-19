package yourestack.epack.business.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.OffsetDateTime;


@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@Table(name = "addresses")
public class AddressEntity {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long addressId;

    @Column(nullable = false, length = 45)
    private String country;

    @Column(nullable = false, length = 45)
    private String region;

    @Column(nullable = false, length = 45)
    private String city;

    @Column(nullable = false)
    private String street;

    @Column(nullable = false, length = 45)
    private String houseNumber;

    @Column(length = 45)
    private String apartmentNumber;

    @Column(nullable = false, length = 45)
    private String postalCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private OffsetDateTime dateCreated;

    @LastModifiedDate
    @Column(nullable = false)
    private OffsetDateTime lastUpdated;

}
