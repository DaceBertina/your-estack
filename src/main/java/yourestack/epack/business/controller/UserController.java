package yourestack.epack.business.controller;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
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

    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @GetMapping("/aboutus")
    public String showGenInfo() {

        return "aboutus";
    }

//    @GetMapping("/allUsers")
//    public String list(final Model model) {
//        model.addAttribute("users", userService.findAll());
//        return "allUsers";
//    }

    @PostMapping("/registerClient")
    public String registerNewClient(@Valid @ModelAttribute("user") final UserDTO user, @NotNull Model model,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            log.error("New client cannot be registered: error in {}", bindingResult);
            return "/signupForm";
        }

        if (userService.userExists(user.getEmail())) {
            bindingResult.addError(new FieldError("user", "username", "There already exists account with that email." ));
        }

        if (user.getPassword() != null && user.getPasswordConfirmation() != null) {
            if (!user.getPassword().equals(user.getPasswordConfirmation())) {
                log.error("Passwords don't match");
                return "signupForm";
            }
        }

        String encodedPassword = encoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        userService.registerNewUser(user);
        redirectAttributes.addFlashAttribute(WebUtil.MSG_SUCCESS, WebUtil.getMessage("user.register.success"));
        return "profile";
    }

    @GetMapping("/editProfile")
    public String editForm(@AuthenticationPrincipal UserDetailsImpl loggedUser, @NotNull Model model) {
        UserDTO user = userService.findByEmail(loggedUser.getEmail());
        model.addAttribute("user", user);
        return "editProfile";
    }

    @PostMapping("/editUserProfile")
    public String editProfile(@AuthenticationPrincipal UserDetailsImpl loggedUser, @NotNull Model model, UserDTO user) {
        Long userId = loggedUser.getId();
        model.addAttribute("user", user);
        userService.update(userId, user);
        return "profile1";
    }

    @GetMapping("/changePassword")
    public String passwordForm(@AuthenticationPrincipal UserDetailsImpl loggedUser, @NotNull Model model) {
        UserDTO user = userService.findByEmail(loggedUser.getEmail());
        model.addAttribute("user", user);
        model.addAttribute("loggedUser", loggedUser);
        return "changePassword";
    }
    @PostMapping("/changePassword")
    public String changePassword(@AuthenticationPrincipal UserDetailsImpl loggedUser, @NotNull Model model, @Valid UserDTO user,
                                 Errors errors, boolean currentPasswordMatches) {
        model.addAttribute("user", user);
        model.addAttribute("errors", errors);
        model.addAttribute("loggedUser", loggedUser);
        model.addAttribute("currentPasswordMatches", currentPasswordMatches);

        if (errors.hasErrors()) {
            return "changePassword";
        }

        if (user.getOldPassword() != null && loggedUser.getPassword() != null) {
            if (!encoder.matches(user.getOldPassword(), loggedUser.getPassword())) {
                log.error("Wrong current password.");
                currentPasswordMatches = false;
                model.addAttribute("currentPasswordMatches", currentPasswordMatches);
                return "changePassword";
            } else {
                currentPasswordMatches = true;
                model.addAttribute("currentPasswordMatches", currentPasswordMatches);
            }
        }

        if (user.getNewPassword() != null && user.getPasswordConfirmation() != null) {
            if (!user.getNewPassword().equals(user.getPasswordConfirmation())) {
                log.error("Passwords don't match");
                return "changePassword";
            }
        }

        Long userId = loggedUser.getId();

        String encodedPassword = encoder.encode(user.getNewPassword());
        user.setPassword(encodedPassword);

        userService.changePassword(userId, user);
        return "changePassword";
    }

    @GetMapping("/deleteProfile")
    public String deleteProfile(@AuthenticationPrincipal UserDetailsImpl loggedUser, @NotNull Model model) {
        UserDTO user = userService.findByEmail(loggedUser.getEmail());
        model.addAttribute("user", user);
        model.addAttribute("loggedUser", loggedUser);
        return "deleteProfile";
    }

    @PostMapping("/deleteClient")
    public String delete(@AuthenticationPrincipal UserDetailsImpl loggedUser, @NotNull Model model,
            final RedirectAttributes redirectAttributes) {
        UserDTO user = userService.findByEmail(loggedUser.getEmail());
        model.addAttribute("user", user);
        model.addAttribute("loggedUser", loggedUser);
        Long clientId = loggedUser.getId();
//        final String referencedWarning = userService.getReferencedWarning(clientId);
//        if (referencedWarning != null) {
//            redirectAttributes.addFlashAttribute(WebUtil.MSG_ERROR, referencedWarning);
//        } else {
            userService.delete(clientId);
            redirectAttributes.addFlashAttribute(WebUtil.MSG_INFO, WebUtil.getMessage("client.delete.success"));
        //}
        return "redirect:/perform_logout";
    }

    @GetMapping("/signupForm")
    public String showSignUp(@ModelAttribute UserDTO user, Model model) {
        model.addAttribute("user",new UserDTO());
        return "signupForm";
    }

    @GetMapping("/loginForm")
    public String showLoginForm(@NotNull Model model) {
        model.addAttribute("user", new UserDTO());
        return "loginForm";
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

    @GetMapping("/perform_logout")
    public String logout(){

        return "/logoutForm";
    }

}

