package yourestack.epack.business.controller;

import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import yourestack.epack.business.domain.EpackDTO;
import yourestack.epack.business.domain.OrderDTO;
import yourestack.epack.business.domain.UserDTO;
import yourestack.epack.business.domain.UserDetailsImpl;
import yourestack.epack.business.service.impl.EpackServiceImpl;
import yourestack.epack.business.service.impl.OrderServiceImpl;
import yourestack.epack.business.service.impl.UserServiceImpl;

import java.time.LocalDateTime;
import java.util.List;

@Log4j2
@Controller
@RequiredArgsConstructor
public class OrderController {

    private final OrderServiceImpl orderService;

    private final UserServiceImpl userService;

    private final EpackServiceImpl epackService;


    @GetMapping("/orderForm")
    public String showOrderForm(@ModelAttribute("epack") EpackDTO epack) {

        return "orderForm";
    }

    @PostMapping("/order")
    public String fillOrder(@AuthenticationPrincipal UserDetailsImpl loggedUser, @ModelAttribute("order") OrderDTO order) {
        UserDTO user = userService.findByEmail(loggedUser.getEmail());
        EpackDTO epack = epackService.findEpackById(order.getEpackId());
        order.setUserId(user.getUserId());
        order.setEpackDTO(epack);
        order.setEpackPrice(order.getEpackPrice());
        order.setDateCreated(LocalDateTime.now());
        orderService.registerNewOrder(order, user, epack);

        return "orderConfirmation";
    }

    @GetMapping("/showOrders")
    public String showOrder(@AuthenticationPrincipal UserDetailsImpl user, Model model) {
        List<OrderDTO> orders = user.getOrderList();
        List<OrderDTO> allOrders = orderService.findAll();
        for (OrderDTO order : allOrders) {
            if (order.getUserId().equals(user.getId())) {
                orders.add(order);
            }
        }
        model.addAttribute("orders", orders);
        return "orders";
    }

    @GetMapping("/allOrders")
    public String getAllOrders(@NotNull Model model) {
        List<OrderDTO> allOrders = orderService.findAll();
        model.addAttribute("allOrders", allOrders);
        return "allOrders";
    }

    @RequestMapping("/orders/{id}")
    @ResponseBody
    public OrderDTO findById(Long id) {
        return orderService.findById(id);
    }
}
