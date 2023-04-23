package yourestack.epack.business.service;

import yourestack.epack.business.domain.OrderDTO;

import java.util.List;

public interface OrderService {

    OrderDTO registerNewOrder(OrderDTO order);

    List<OrderDTO> findAll();

    OrderDTO findById(final Long orderId);
}
