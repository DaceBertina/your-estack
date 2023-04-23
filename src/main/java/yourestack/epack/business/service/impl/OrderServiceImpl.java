package yourestack.epack.business.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import yourestack.epack.business.domain.OrderDTO;
import yourestack.epack.business.mappers.OrderMapStruct;
import yourestack.epack.business.model.OrderEntity;
import yourestack.epack.business.model.repos.OrderRepository;
import yourestack.epack.business.model.repos.UserRepository;
import yourestack.epack.business.service.OrderService;
import yourestack.epack.util.NotFoundException;

import java.util.List;
import java.util.stream.Collectors;

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
    public List<OrderDTO> findAll() {
        final List<OrderEntity> orders = orderRepository.findAll(Sort.by("epackId"));
        return orders.stream()
                .map((order) -> orderMapper.orderEntityToOrderDto(order))
                .collect(Collectors.toList());
    }

    @Override
    public OrderDTO findById(final Long orderId) {
        return orderRepository.findById(orderId)
                .map(order -> orderMapper.orderEntityToOrderDto(order))
                .orElseThrow(NotFoundException::new);
    }

}