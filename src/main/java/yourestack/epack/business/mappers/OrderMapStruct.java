package yourestack.epack.business.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import yourestack.epack.business.domain.EpackDTO;
import yourestack.epack.business.domain.OrderDTO;
import yourestack.epack.business.model.EpackEntity;
import yourestack.epack.business.model.OrderEntity;
import yourestack.epack.business.model.UserEntity;

@Mapper(componentModel = "spring")
public interface OrderMapStruct {

    OrderMapStruct INSTANCE = Mappers.getMapper(OrderMapStruct.class);

    @Mapping(source = "epackDTO", target = "epackEntity.epackId", qualifiedByName = "epackIdEntityMapper")
    @Mapping(source = "userEmail", target = "userEntity", qualifiedByName = "userEntityByEmailMapper")
    OrderEntity orderDtoToOrderEntity(OrderDTO orderDto);

    @Mapping(source = "epackEntity.epackId", target = "epackDTO", qualifiedByName = "epackEntityByIdMapper")
    @Mapping(source = "userEntity", target = "userEmail", qualifiedByName = "userEmailEntityMapper")
    OrderDTO orderEntityToOrderDto(OrderEntity orderEntity);

    @Named("epackIdEntityMapper")
    static Long epackIdEntityMapper(EpackDTO epackDTO) {
        if (epackDTO == null) return null;
        return epackDTO.getEpackId();
    }

    @Named("epackEntityByIdMapper")
    static EpackDTO epackEntityByIdMapper(Long epackId) {
        if (epackId == null) return null;
        return new EpackDTO(epackId);
    }

    @Named("userEmailEntityMapper")
    static String userEmailEntityMapper(UserEntity userEntity) {
        if (userEntity == null) return null;
        return userEntity.getEmail();
    }

    @Named("userEntityByEmailMapper")
    static UserEntity userEntityByEmailMapper(String email) {
        if (email == null) return null;
        return new UserEntity(email);
    }
}
