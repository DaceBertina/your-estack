package yourestack.epack.business.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import yourestack.epack.business.domain.WorkplaceDTO;
import yourestack.epack.business.model.WorkplaceEntity;

@Mapper(componentModel = "spring")
public interface WorkplaceMapStruct {

    WorkplaceMapStruct INSTANCE = Mappers.getMapper(WorkplaceMapStruct.class);

    WorkplaceEntity workplaceDtoToWorkplaceEntity(WorkplaceDTO workplaceDto);

    WorkplaceDTO workplaceEntityToWorkplaceDto(WorkplaceEntity workplaceEntity);

    @Mapping(target = "workplaceId", ignore = true)
    WorkplaceEntity updateEntity(WorkplaceDTO workplaceDto, @MappingTarget WorkplaceEntity workplaceEntity);

}
