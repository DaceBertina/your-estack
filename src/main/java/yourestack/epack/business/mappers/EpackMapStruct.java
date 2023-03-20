package yourestack.epack.business.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import yourestack.epack.business.domain.EpackDTO;
import yourestack.epack.business.model.CategoryEntity;
import yourestack.epack.business.model.EpackEntity;

@Mapper(componentModel = "spring")
public interface EpackMapStruct {

    EpackMapStruct INSTANCE = Mappers.getMapper(EpackMapStruct.class);

    @Mapping(source="categoryId", target="categoryEntity", qualifiedByName = "categoryIdToCategoryEntity")
    EpackEntity epackDtoToEpackEntity(EpackDTO epackDto);

    @Mapping(source="categoryEntity", target="categoryId", qualifiedByName = "categoryEntityToCategoryId")
    EpackDTO epackEntityToEpackDto(EpackEntity epackEntity);

    @Named("categoryIdToCategoryEntity")
    static CategoryEntity categoryIdToCategoryEntity(Long  categoryId) {
        if (categoryId == null) return null;
        return new CategoryEntity(categoryId);
    }

    @Named("categoryEntityToCategoryId")
    static Long categoryEntityToCategoryId(CategoryEntity categoryEntity){
        if (categoryEntity == null) return null;
        return categoryEntity.getCategoryId();
    }

}
