package yourestack.epack.business.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import yourestack.epack.business.domain.OrderDTO;
import yourestack.epack.business.model.OrderEntity;

@Mapper(componentModel = "spring")
public interface OrderMapStruct {

    OrderMapStruct INSTANCE = Mappers.getMapper(OrderMapStruct.class);

    OrderEntity orderDtoToOrderEntity(OrderDTO orderDto);

    OrderDTO orderEntityToOrderDto(OrderEntity orderEntity);
}
