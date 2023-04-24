package yourestack.epack.business.service;

import yourestack.epack.business.domain.OrderDTO;
import yourestack.epack.business.domain.UserDTO;
import yourestack.epack.business.domain.UserDetailsImpl;

import java.util.List;

public interface OrderService {

    void registerNewOrder(OrderDTO order, UserDTO user);

    List<OrderDTO> findAll();

    OrderDTO findById(final Long orderId);
}
