package yourestack.epack.business.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import yourestack.epack.business.domain.OrderDTO;
import yourestack.epack.business.mappers.OrderMapStruct;
import yourestack.epack.business.model.OrderEntity;
import yourestack.epack.business.model.repos.OrderRepository;
import yourestack.epack.business.model.repos.UserRepository;
import yourestack.epack.business.service.OrderService;

@Log4j2
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapStruct orderMapper;

    private final UserRepository userRepository;

    @Override
    public OrderDTO registerNewOrder(OrderDTO order) {

        OrderEntity orderEntity = orderRepository.save(orderMapper.orderDtoToOrderEntity(order));
        log.info("New order registered: {}", orderEntity);

        return orderMapper.orderEntityToOrderDto(orderEntity);
    }

    @Override
    public OrderDTO findByUserEmail(String email) {
        OrderEntity orderEntity = null;
        if (userExists(email)) {
            orderEntity = orderRepository.findByUserEmail(email);
        } else {
            throw new HttpClientErrorException(HttpStatus.CONFLICT, "User with email " + email + " does not exist.");
        }

        return orderMapper.orderEntityToOrderDto(orderEntity);
    }

    public boolean userExists(String username) {
        return userRepository.findByEmail(username).isPresent();
    }
}
