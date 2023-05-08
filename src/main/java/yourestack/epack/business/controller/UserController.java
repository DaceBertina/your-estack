package yourestack.epack.business.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import yourestack.epack.business.domain.EpackDTO;
import yourestack.epack.business.domain.OrderDTO;
import yourestack.epack.business.domain.UserDTO;
import yourestack.epack.business.domain.UserDetailsImpl;
import yourestack.epack.business.service.impl.EpackServiceImpl;
import yourestack.epack.business.service.impl.OrderServiceImpl;
import yourestack.epack.business.service.impl.UserServiceImpl;
import yourestack.epack.util.WebUtil;

import java.util.ArrayList;
import java.util.List;

@Log4j2
@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserServiceImpl userService;

    private final OrderServiceImpl orderService;

    private final EpackServiceImpl epackService;

    @GetMapping("/aboutus")
    public String showGenInfo() {

        return "aboutus";
    }

    @GetMapping("/allUsers")
    public String list(final Model model) {
        model.addAttribute("users", userService.findAll());
        return "allUsers";
    }

    @PostMapping("/registerClient")
    public String registerNewClient(@ModelAttribute("user") @Valid final UserDTO user,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            log.error("New client cannot be registered: error in {}", bindingResult);
            return "/signupForm";
        }

        if (userService.userExists(user.getEmail())) {
            bindingResult.addError(new FieldError("client", "username", "There already exists account with that email." ));
        }


//        if (client.getPassword() != null && client.getMatchingPassword() != null) {
//            if (!client.getPassword().equals(client.getMatchingPassword())) {
//                result.addError(new FieldError("client", "password", "Passwords don't match." ));
//            }
//        }

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        userService.registerNewUser(user);
        redirectAttributes.addFlashAttribute(WebUtil.MSG_SUCCESS, WebUtil.getMessage("user.register.success"));
        return "profile";
    }

    @GetMapping("editProfile")
    public String editForm(WebRequest request, @ModelAttribute UserDTO user, Model model) {
        model.addAttribute("user",new UserDTO());
        return "editProfile";
    }

    @PostMapping("/editUserProfile")
    public String editProfile(@ModelAttribute("user") @Valid final UserDTO user,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "editProfile";
        }
        userService.update(user);
        redirectAttributes.addFlashAttribute(WebUtil.MSG_SUCCESS, WebUtil.getMessage("client.update.success"));
        return "profile";
    }

    @PostMapping("/delete/{clientId}")
    public String delete(@PathVariable final Long clientId,
            final RedirectAttributes redirectAttributes) {
        final String referencedWarning = userService.getReferencedWarning(clientId);
        if (referencedWarning != null) {
            redirectAttributes.addFlashAttribute(WebUtil.MSG_ERROR, referencedWarning);
        } else {
            userService.delete(clientId);
            redirectAttributes.addFlashAttribute(WebUtil.MSG_INFO, WebUtil.getMessage("client.delete.success"));
        }
        return "redirect:/clients";
    }

    @GetMapping("signupForm")
    public String showSignUp(WebRequest request, @ModelAttribute UserDTO user, Model model) {
        model.addAttribute("user",new UserDTO());
        return "signupForm";
    }

    @GetMapping("loginForm")
    public String showLoginForm() {
        return "loginForm";
    }

    @GetMapping("/showProfile")
    public String showProfile(@AuthenticationPrincipal UserDetailsImpl user, Model model) {
        model.addAttribute("user", user);
        return "profile1";
    }

    @GetMapping("/profile1")
    public String viewProfile(@AuthenticationPrincipal UserDetailsImpl user, Model model) {
        List<OrderDTO> orders = new ArrayList<>();
        List<EpackDTO> epacks = new ArrayList<>();
        List<OrderDTO> allOrders = orderService.findAll();
        for (OrderDTO order : allOrders) {
            if (order.getUserId().equals(user.getId())) {
                orders.add(order);
                epacks.add(epackService.findEpackById(order.getEpackId()));
            }
        }
        model.addAttribute("user", user);
        model.addAttribute("orders", orders);
        model.addAttribute("epacks", epacks);
        return "/profile1";
    }

    @GetMapping("/login?error=true")
    public String loginError(Model model) {
        model.addAttribute("error", "error");
        return "loginForm";
    }

//    @GetMapping("/showYourEpacks")
//    public String showOrder(@AuthenticationPrincipal UserDetailsImpl user, Model model) {
//        List<OrderDTO> orders = user.getOrders();
//        List<OrderDTO> allOrders = orderService.findAll();
//        for (OrderDTO order : allOrders) {
//            if (order.getUserId().equals(user.getId())) {
//                orders.add(order);
//            }
//        }
//        model.addAttribute("orders", orders);
//        return "orders";
//    }
}
