package yourestack.epack.business.controller;

import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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

    @PostMapping("/feedbackJava")
    public String saveFeedbackJava(@AuthenticationPrincipal UserDetailsImpl loggedUser,
                                    @ModelAttribute FeedbackJava feedbackJava,
                                    @NotNull Model model) {
        UserDTO user = userService.findByEmail(loggedUser.getEmail());
        List<EpackDTO> allEpacks = epackService.findAll();
        model.addAttribute("allEpacks", allEpacks);

        List<FeedbackDTO> allJavaFeedbacks = feedbackService.findAllJavaFeedbacks();
        List<FeedbackDTO> allSpringFeedbacks = feedbackService.findAllSpringFeedbacks();
        List<FeedbackDTO> allMicroFeedbacks = feedbackService.findAllSpringFeedbacks();
        allJavaFeedbacks.add(feedbackJava);
        model.addAttribute("allJavaFeedbacks", allJavaFeedbacks);
        model.addAttribute("allSpringFeedbacks", allSpringFeedbacks);
        model.addAttribute("allMicroFeedbacks", allMicroFeedbacks);
        FeedbackDTO feedbackSpring = new FeedbackSpring();
        FeedbackDTO feedbackMicro = new FeedbackMicro();
        model.addAttribute("feedbackJava", feedbackJava);
        model.addAttribute("feedbackSpring", feedbackSpring);
        model.addAttribute("feedbackMicro", feedbackMicro);
        feedbackJava.setEpackId(1L);
        feedbackJava.setFeedText (feedbackJava.getFeedText());
        feedbackService.saveFeedback(feedbackJava, user);
        return "allEpacks";
    }

    @GetMapping("/allJavaFeedbacks")
    public String allFeedbacksJava(@NotNull Model model) {
        List<FeedbackDTO> allJavaFeedbacks = feedbackService.findAllJavaFeedbacks();
        List<EpackDTO> allEpacks = epackService.findAll();
        model.addAttribute("allEpacks", allEpacks);
        model.addAttribute("allJavaFeedbacks", allJavaFeedbacks);
        return "allEpacks";
    }

    @PostMapping("/feedbackSpring")
    public String saveFeedbackSpring(@AuthenticationPrincipal UserDetailsImpl loggedUser,
                                   @ModelAttribute FeedbackSpring feedbackSpring,
                                   @NotNull Model model) {
        UserDTO user = userService.findByEmail(loggedUser.getEmail());
        List<EpackDTO> allEpacks = epackService.findAll();
        model.addAttribute("allEpacks", allEpacks);

        List<FeedbackDTO> allJavaFeedbacks = feedbackService.findAllJavaFeedbacks();
        List<FeedbackDTO> allSpringFeedbacks = feedbackService.findAllSpringFeedbacks();
        List<FeedbackDTO> allMicroFeedbacks = feedbackService.findAllSpringFeedbacks();
        allSpringFeedbacks.add(feedbackSpring);
        model.addAttribute("allJavaFeedbacks", allJavaFeedbacks);
        model.addAttribute("allSpringFeedbacks", allSpringFeedbacks);
        model.addAttribute("allMicroFeedbacks", allMicroFeedbacks);
        FeedbackDTO feedbackJava = new FeedbackJava();
        FeedbackDTO feedbackMicro = new FeedbackMicro();
        model.addAttribute("feedbackJava", feedbackJava);
        model.addAttribute("feedbackSpring", feedbackSpring);
        model.addAttribute("feedbackMicro", feedbackMicro);
        feedbackSpring.setEpackId(2L);
        feedbackSpring.setFeedText (feedbackSpring.getFeedText());
        feedbackService.saveFeedback(feedbackSpring, user);
        return "allEpacks";
    }

    @GetMapping("/allSpringFeedbacks")
    public String allFeedbacksSpring(@NotNull Model model) {
        List<FeedbackDTO> allSpringFeedbacks = feedbackService.findAllSpringFeedbacks();
        List<EpackDTO> allEpacks = epackService.findAll();
        model.addAttribute("allEpacks", allEpacks);
        model.addAttribute("allSpringFeedbacks", allSpringFeedbacks);
        return "allEpacks";
    }

    @PostMapping("/feedbackMicro")
    public String saveFeedbackMicro(@AuthenticationPrincipal UserDetailsImpl loggedUser,
                                   @ModelAttribute FeedbackMicro feedbackMicro,
                                   @NotNull Model model) {
        UserDTO user = userService.findByEmail(loggedUser.getEmail());
        List<EpackDTO> allEpacks = epackService.findAll();
        model.addAttribute("allEpacks", allEpacks);

        List<FeedbackDTO> allJavaFeedbacks = feedbackService.findAllJavaFeedbacks();
        List<FeedbackDTO> allSpringFeedbacks = feedbackService.findAllSpringFeedbacks();
        List<FeedbackDTO> allMicroFeedbacks = feedbackService.findAllMicroFeedbacks();
        allMicroFeedbacks.add(feedbackMicro);
        model.addAttribute("allJavaFeedbacks", allJavaFeedbacks);
        model.addAttribute("allSpringFeedbacks", allSpringFeedbacks);
        model.addAttribute("allMicroFeedbacks", allMicroFeedbacks);
        FeedbackDTO feedbackSpring = new FeedbackSpring();
        FeedbackDTO feedbackJava = new FeedbackJava();
        model.addAttribute("feedbackJava", feedbackJava);
        model.addAttribute("feedbackSpring", feedbackSpring);
        model.addAttribute("feedbackMicro", feedbackMicro);
        feedbackMicro.setEpackId(3L);
        feedbackMicro.setFeedText (feedbackMicro.getFeedText());
        feedbackService.saveFeedback(feedbackMicro, user);
        return "allEpacks";
    }

    @GetMapping("/allMicroFeedbacks")
    public String allFeedbacksMicro(@NotNull Model model) {
        List<FeedbackDTO> allMicroFeedbacks = feedbackService.findAllMicroFeedbacks();
        List<EpackDTO> allEpacks = epackService.findAll();
        model.addAttribute("allEpacks", allEpacks);
        model.addAttribute("allMicroFeedbacks", allMicroFeedbacks);
        return "allEpacks";
    }

}
