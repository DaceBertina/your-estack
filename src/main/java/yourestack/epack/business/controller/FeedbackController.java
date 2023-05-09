package yourestack.epack.business.controller;

import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import yourestack.epack.business.domain.*;
import yourestack.epack.business.service.impl.EpackServiceImpl;
import yourestack.epack.business.service.impl.FeedbackServiceImpl;
import yourestack.epack.business.service.impl.UserServiceImpl;

import java.util.List;

@Log4j2
@Controller
@RequiredArgsConstructor
public class FeedbackController {

    private final FeedbackServiceImpl feedbackService;
    private final UserServiceImpl userService;

    private final EpackServiceImpl epackService;

    @PostMapping("/feedback")
    public String saveFeedback(@AuthenticationPrincipal UserDetailsImpl loggedUser,
                               @NotNull Model model, EpackDTO epack, FeedbackDTO feedback) {

        if (loggedUser.getId() == null) {
            log.error("User is not authenticated.");
            return "/loginRequest";
        }

        UserDTO user = userService.findByEmail(loggedUser.getEmail());
        List<EpackDTO> allEpacks = epackService.findAll();
        model.addAttribute("feedback", feedback);
        model.addAttribute("epack", epack);
        model.addAttribute("allEpacks", allEpacks);
        feedbackService.saveFeedback(feedback, user);
        return "allEpacks";
    }
}
