package yourestack.epack.business.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import yourestack.epack.business.domain.EpackDTO;
import yourestack.epack.business.domain.OrderDTO;
import yourestack.epack.business.domain.UserDTO;
import yourestack.epack.business.mappers.EpackMapStruct;
import yourestack.epack.business.mappers.OrderMapStruct;
import yourestack.epack.business.mappers.UserMapStruct;
import yourestack.epack.business.model.OrderEntity;
import yourestack.epack.business.model.repos.OrderRepository;
import yourestack.epack.business.model.repos.UserRepository;
import yourestack.epack.business.service.OrderService;
import yourestack.epack.util.NotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapStruct orderMapper;

    private final UserMapStruct userMapper;

    private final EpackMapStruct epackMapper;

    private final UserRepository userRepository;

    @Override
    public void registerNewOrder(OrderDTO order, UserDTO user, EpackDTO epack) {
        order.setEpackId(epack.getEpackId());
        order.setEpackDTO(epack);
        order.setUserId(user.getUserId());
        order.setEpackPrice(epack.getPrice());
        OrderEntity orderEntity = orderRepository.save(orderMapper.orderDtoToOrderEntity(order));
        if (user.getOrderList() != null) {
            user.getOrderList().add(orderMapper.orderEntityToOrderDto(orderEntity));
        } else {
            addFirstOrderToList(user, order);
        }
        log.info("New order registered: {}", orderEntity);
        log.info("Epack ID and User ID: {} {}", epack.getEpackId(), user.getUserId());
    }

    private void addFirstOrderToList(UserDTO user, OrderDTO order) {
        List<OrderDTO> initList = new ArrayList<>();
        if (order != null) {
            initList.add(order);
        }
        if (user != null) {
            user.setOrderList(initList);
        }
    }

    @Override
    public List<OrderDTO> findAll() {
        final List<OrderEntity> orders = orderRepository.findAll();
        return orders.stream()
                .map(orderMapper::orderEntityToOrderDto)
                .collect(Collectors.toList());
    }

    @Override
    public OrderDTO findById(final Long orderId) {
        return orderRepository.findById(orderId)
                .map(orderMapper::orderEntityToOrderDto)
                .orElseThrow(NotFoundException::new);
    }

}