package yourestack.epack.business.domain;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class AddressDTO {

    private Long addressId;

    @NotNull
    @Size(max = 45)
    private String country;

    @NotNull
    @Size(max = 45)
    private String region;

    @NotNull
    @Size(max = 45)
    private String city;

    @NotNull
    @Size(max = 255)
    private String street;

    @NotNull
    @Size(max = 45)
    private String houseNumber;

    @Size(max = 45)
    private String apartmentNumber;

    @NotNull
    @Size(max = 45)
    private String postalCode;

    @NotNull
    private Long client;

}
