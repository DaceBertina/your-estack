package yourestack.epack.business.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import yourestack.epack.business.domain.RoleDTO;
import yourestack.epack.business.model.RoleEntity;

@Mapper(componentModel = "spring")
public interface RoleMapStruct {

    RoleMapStruct INSTANCE = Mappers.getMapper(RoleMapStruct.class);

    RoleEntity roleDtoToRoleEntity(RoleDTO roleDto);

    RoleDTO roleEntityToRoleDto(RoleEntity roleEntity);
}
