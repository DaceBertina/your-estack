package yourestack.epack.business.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import yourestack.epack.business.domain.UserDTO;
import yourestack.epack.business.domain.UserDetailsImpl;
import yourestack.epack.business.service.impl.UserServiceImpl;
import yourestack.epack.config.AuthenticationFacade;
import yourestack.epack.config.IAuthenticationFacade;
import yourestack.epack.util.WebUtil;

@Log4j2
@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserServiceImpl userService;

//    private final AuthenticationFacade authenticationFacade;

    @GetMapping
    public String list(final Model model) {
        model.addAttribute("users", userService.findAll());
        return "user/list";
    }

    @GetMapping("/add")
    public String add(@ModelAttribute("user") final UserDTO clientDTO) {
        return "user/add";
    }

    @PostMapping("/registerClient")
    public String registerNewClient(@ModelAttribute("user") @Valid final UserDTO user,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            log.error("New client cannot be registered: error in {}", bindingResult);
            return "/signupForm";
        }

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        userService.registerNewUser(user);
        redirectAttributes.addFlashAttribute(WebUtil.MSG_SUCCESS, WebUtil.getMessage("user.register.success"));
        return "profile";
    }

//    @PostMapping("/user/registration")
//    public ModelAndView registerUserAccount(
//            @ModelAttribute("userDTO") @Valid UserDTO userDto,
//            HttpServletRequest request,
//            Errors errors) {
//
//            UserDTO registered = userService.registerNewUser(userDto);
//
//        return new ModelAndView("profile", "userDTO", userDto);
//    }

    @GetMapping("/edit/{clientId}")
    public String edit(@PathVariable final Long clientId, final Model model) {
        model.addAttribute("client", userService.get(clientId));
        return "client/edit";
    }

    @PostMapping("/edit/{clientId}")
    public String edit(@PathVariable final Long clientId,
            @ModelAttribute("client") @Valid final UserDTO clientDTO,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "client/edit";
        }
        userService.update(clientId, clientDTO);
        redirectAttributes.addFlashAttribute(WebUtil.MSG_SUCCESS, WebUtil.getMessage("client.update.success"));
        return "redirect:/clients";
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

    @PostMapping("/registration")
    public String saveUser(@Valid UserDTO client, BindingResult result, RedirectAttributes ra) {
        if (userService.userExists(client.getEmail())) {
            result.addError(new FieldError("client", "username", "There already exists account with that email." ));
        }

//        if (client.getPassword() != null && client.getMatchingPassword() != null) {
//            if (!client.getPassword().equals(client.getMatchingPassword())) {
//                result.addError(new FieldError("client", "password", "Passwords don't match." ));
//            }
//        }

        if (result.hasErrors()) {
            return "signupForm";
        }
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(client.getPassword());
        client.setPassword(encodedPassword);
        ra.addFlashAttribute("message", "You have been registered successfully.");
        userService.registerNewUser(client);

        return "profile";
    }

//    @GetMapping("loginForm")
//    public String showLoginForm(@ModelAttribute("user") UserDTO user, Model model) {
//        model.addAttribute("user",new UserDTO());
//        return "loginForm";
//    }

    @GetMapping("loginForm")
    public String showLoginForm() {
        return "loginForm";
    }

    @Autowired
    private IAuthenticationFacade authenticationFacade;

    @Autowired
    private UserServiceImpl userServiceImpl;

    @GetMapping("/perform_login")
    public String processLogin(Model model) {
        log.info("Login successful.");
        Authentication authentication = authenticationFacade.getAuthentication();
        String email = authentication.getName();
        UserDTO user = userServiceImpl.findByEmail(email);
        log.info("Email: {}", email);
        model.addAttribute("user", user);

        return "redirect:/profile1";
    }

    @GetMapping("/showProfile")
    public String showProfile(@AuthenticationPrincipal UserDetailsImpl user, Model model) {
        model.addAttribute("user", user);
        return "user";
    }

    @GetMapping("/profile1")
    public String viewProfile(@AuthenticationPrincipal UserDetailsImpl user, Model model) {
        model.addAttribute("user", user);
        return "/profile1";
    }

    @GetMapping("/login-error")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "loginForm";
    }

}
