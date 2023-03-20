package yourestack.epack.business.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import yourestack.epack.business.domain.CategoryDTO;
import yourestack.epack.business.model.CategoryEntity;

@Mapper(componentModel = "spring")
public interface CategoryMapStruct {

    CategoryMapStruct INSTANCE = Mappers.getMapper(CategoryMapStruct.class);

    CategoryEntity categoryDtoToCategoryEntity(CategoryDTO categoryDto);

    CategoryDTO categoryEntityToCategoryDto(CategoryEntity categoryEntity);
}
