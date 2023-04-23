package yourestack.epack.business.mappers;

import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import yourestack.epack.business.domain.OrderDTO;
import yourestack.epack.business.domain.RoleDTO;
import yourestack.epack.business.domain.RoleEnum;
import yourestack.epack.business.domain.UserDTO;
import yourestack.epack.business.model.OrderEntity;
import yourestack.epack.business.model.RoleEntity;
import yourestack.epack.business.model.UserEntity;

@Mapper(componentModel = "spring")
public interface UserMapStruct {

    UserMapStruct INSTANCE = Mappers.getMapper(UserMapStruct.class);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source="roleList", target="roleEntityList", qualifiedByName = "roleListDtoMapper")
    @Mapping(source="orderList", target="orderEntityList", qualifiedByName = "ordersListDtoMapper")
    //@Mapping(target = "authorities", ignore = true)
//    @Mapping(source="role", target="roleEntity.id", qualifiedByName = "roleEnumDtoMapper")
    UserEntity userDtoToUserEntity(UserDTO userDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source="roleEntityList", target="roleList", qualifiedByName = "roleListEntityMapper")
    @Mapping(source="orderEntityList", target="orderList", qualifiedByName = "ordersListEntityMapper")
//    @Mapping(source="roleEntity.id", target="role", qualifiedByName = "roleEntityToEnumMapper")
    UserDTO userEntityToUserDto(UserEntity userEntity);

    @Mapping(target = "userId", ignore = true)
    @Mapping(target = "dateCreated", ignore = true)
    UserEntity updateEntity(UserDTO userDto, @MappingTarget UserEntity userEntity);

    @Named("roleListEntityMapper")
    static RoleDTO roleListEntityMapper(RoleEntity roleEntity){
        return RoleMapStruct.INSTANCE.roleEntityToRoleDto(roleEntity);
    }

    @Named("roleListDtoMapper")
    static RoleEntity roleListDtoMapper(RoleDTO role){
        return RoleMapStruct.INSTANCE.roleDtoToRoleEntity(role);
    }
    @Named("roleEntityToEnumMapper")
    static RoleEnum roleEntityToEnumMapper(Long  roleEntityId) {
        return RoleEnum.castLongToEnum(roleEntityId);
    }

    @Named("roleEnumDtoMapper")
    static Long roleEnumDtoMapper(RoleEnum role){
        return role.getId();
    }

    @Named("ordersListEntityMapper")
    static OrderDTO ordersListEntityMapper(OrderEntity orderEntity){
        return OrderMapStruct.INSTANCE.orderEntityToOrderDto(orderEntity);
    }

    @Named("ordersListDtoMapper")
    static OrderEntity ordersListDtoMapper(OrderDTO orderDTO){
        return OrderMapStruct.INSTANCE.orderDtoToOrderEntity(orderDTO);
    }

}
