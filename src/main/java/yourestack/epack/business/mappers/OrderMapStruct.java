package yourestack.epack.business.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import yourestack.epack.business.domain.OrderDTO;
import yourestack.epack.business.model.EpackEntity;
import yourestack.epack.business.model.OrderEntity;
import yourestack.epack.business.model.UserEntity;

@Mapper(componentModel = "spring")
public interface OrderMapStruct {

    OrderMapStruct INSTANCE = Mappers.getMapper(OrderMapStruct.class);

    @Mapping(source = "epackId", target = "epackEntity", qualifiedByName = "epackEntityByIdMapper")
//    @Mapping(source = "userId", target = "userEntity", qualifiedByName = "userEntityByIdMapper")
    OrderEntity orderDtoToOrderEntity(OrderDTO orderDto);

    @Mapping(source = "epackEntity", target = "epackId", qualifiedByName = "epackIdEntityMapper")
//    @Mapping(source = "userEntity", target = "userId", qualifiedByName = "userIdEntityMapper")
    OrderDTO orderEntityToOrderDto(OrderEntity orderEntity);

    @Named("epackIdEntityMapper")
    static Long epackIdEntityMapper(EpackEntity epackEntity) {
        if (epackEntity == null) return null;
        return epackEntity.getEpackId();
    }

    @Named("epackEntityByIdMapper")
    static EpackEntity epackEntityByIdMapper(Long epackId) {
        if (epackId == null) return null;
        return new EpackEntity(epackId);
    }

    @Named("userIdEntityMapper")
    static Long userIdEntityMapper(UserEntity userEntity) {
        if (userEntity == null) return null;
        return userEntity.getUserId();
    }

    @Named("userEntityByIdMapper")
    static UserEntity userEntityByIdMapper(Long userId) {
        if (userId == null) return null;
        return new UserEntity(userId);
    }

}
