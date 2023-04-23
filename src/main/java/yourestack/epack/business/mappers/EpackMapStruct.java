package yourestack.epack.business.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import yourestack.epack.business.domain.EpackDTO;
import yourestack.epack.business.domain.OrderDTO;
import yourestack.epack.business.model.CategoryEntity;
import yourestack.epack.business.model.EpackEntity;
import yourestack.epack.business.model.OrderEntity;

@Mapper(componentModel = "spring")
public interface EpackMapStruct {

    EpackMapStruct INSTANCE = Mappers.getMapper(EpackMapStruct.class);

    @Mapping(source="categoryId", target="categoryEntity", qualifiedByName = "categoryIdToCategoryEntity")
    @Mapping(source="ordersList", target="orderEntityList", qualifiedByName = "ordersListDtoMapper")
    EpackEntity epackDtoToEpackEntity(EpackDTO epackDto);

    @Mapping(source="categoryEntity", target="categoryId", qualifiedByName = "categoryEntityToCategoryId")
    @Mapping(source="orderEntityList", target="ordersList", qualifiedByName = "ordersListEntityMapper")
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

    @Named("ordersListEntityMapper")
    static OrderDTO ordersListEntityMapper(OrderEntity orderEntity){
        return OrderMapStruct.INSTANCE.orderEntityToOrderDto(orderEntity);
    }

    @Named("ordersListDtoMapper")
    static OrderEntity ordersListDtoMapper(OrderDTO orderDTO){
        return OrderMapStruct.INSTANCE.orderDtoToOrderEntity(orderDTO);
    }
}
