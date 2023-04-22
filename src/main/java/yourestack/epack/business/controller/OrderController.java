package yourestack.epack.business.controller;

import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import yourestack.epack.business.domain.OrderDTO;
import yourestack.epack.business.domain.UserDetailsImpl;
import yourestack.epack.business.service.impl.OrderServiceImpl;
import yourestack.epack.business.service.impl.UserServiceImpl;

import java.util.List;
import java.util.Optional;

@Log4j2
@Controller
@RequiredArgsConstructor
public class OrderController {

    private final OrderServiceImpl orderService;

    @GetMapping("/order")
    public String showOrderForm(@NotNull Model model) {

        model.addAttribute("order",new OrderDTO());
        return "order";
    }

    @PostMapping("/fillOrder")
    public String processOrder(OrderDTO order) {
        orderService.registerNewOrder(order);

        return "orderConfirmation";
    }

    @GetMapping("/showOrder")
    public String showOrder(@AuthenticationPrincipal UserDetailsImpl user, Model model) {
        List<OrderDTO> orders = user.getOrders();
        List<OrderDTO> allOrders = orderService.findAll();
        for (OrderDTO order : allOrders) {
            if (order.getUserEmail().equals(user.getEmail())) {
                orders.add(order);
            }
        }
        model.addAttribute("orders", orders);
        return "orders";
    }

    @GetMapping("/allOrders")
    public String getCars(@NotNull Model model) {
        List<OrderDTO> allOrders = orderService.findAll();
        model.addAttribute("allOrders", allOrders);
        return "allOrders";
    }

    @RequestMapping("/orders/{id}")
    @ResponseBody
    public Optional<OrderDTO> findById(Integer id) {
        return orderService.findById(id);
    }
}
