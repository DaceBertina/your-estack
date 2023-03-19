package yourestack.epack.business.mappers;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import yourestack.epack.business.domain.AddressDTO;
import yourestack.epack.business.model.AddressEntity;

@Mapper(componentModel = "spring")
public interface AddressMapStruct {

    AddressMapStruct INSTANCE = Mappers.getMapper(AddressMapStruct.class);

    AddressEntity addressDtoToAddressEntity(AddressDTO addressDto);

    AddressDTO addressEntityToAddressDto(AddressEntity addressEntity);

    @Mapping(target = "addressId", ignore = true)
    AddressEntity updateEntity(AddressDTO addressDto, @MappingTarget AddressEntity addressEntity);

}
