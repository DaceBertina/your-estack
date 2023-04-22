package yourestack.epack.business.service;

import yourestack.epack.business.domain.OrderDTO;

public interface OrderService {

    OrderDTO registerNewOrder(OrderDTO order);

    OrderDTO findByUserEmail(String email);


}
