package yourestack.epack.business.controller;

import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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

    @PostMapping("/feedbackJava")
    public String saveFeedbackJava(@AuthenticationPrincipal UserDetailsImpl loggedUser,
                               @NotNull Model model, FeedbackDTO feedback) {
        UserDTO user = userService.findByEmail(loggedUser.getEmail());
        List<EpackDTO> allEpacks = epackService.findAll();
        model.addAttribute("allEpacks", allEpacks);
        model.addAttribute("feedback", feedback);
        feedbackService.saveFeedback(feedback, user);
        return "allEpacks";
    }

    @GetMapping("/allJavaFeedbacks")
    public String saveFeedbackJava(@NotNull Model model) {
        List<FeedbackDTO> allJavaFeedbacks = feedbackService.findAllJavaFeedbacks();
        List<EpackDTO> allEpacks = epackService.findAll();
        model.addAttribute("allEpacks", allEpacks);
        model.addAttribute("allJavaFeedbacks", allJavaFeedbacks);
        return "allEpacks";
    }

    @PostMapping("/feedbackSpring")
    public String saveFeedbackSpring(@AuthenticationPrincipal UserDetailsImpl loggedUser,
                               @NotNull Model model, FeedbackDTO feedback) {

        UserDTO user = userService.findByEmail(loggedUser.getEmail());
        List<EpackDTO> allEpacks = epackService.findAll();
        model.addAttribute("allEpacks", allEpacks);
        model.addAttribute("feedback", feedback);
        feedbackService.saveFeedback(feedback, user);
        return "allEpacks";
    }

    @PostMapping("/feedbackMicro")
    public String saveFeedbackMicro(@AuthenticationPrincipal UserDetailsImpl loggedUser,
                                     @NotNull Model model, FeedbackDTO feedback) {

        UserDTO user = userService.findByEmail(loggedUser.getEmail());
        List<EpackDTO> allEpacks = epackService.findAll();
        model.addAttribute("allEpacks", allEpacks);
        model.addAttribute("feedback", feedback);
        feedbackService.saveFeedback(feedback, user);
        return "allEpacks";
    }
}
