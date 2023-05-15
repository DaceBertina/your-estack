package yourestack.epack.business.controller;

import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import yourestack.epack.business.domain.*;
import yourestack.epack.business.service.impl.EpackServiceImpl;
import yourestack.epack.business.service.impl.FeedbackServiceImpl;

import java.util.List;

@Log4j2
@Controller
@RequiredArgsConstructor
public class EpackController {

    private final EpackServiceImpl epackService;

    private final FeedbackServiceImpl feedbackService;

    @RequestMapping("/epacks/{id}")
    @ResponseBody
    public EpackDTO findById(Long id) {
        return epackService.findEpackById(id);
    }

    @GetMapping("/allEpacks")
    public String getAllEpacks(@NotNull Model model) {

        List<EpackDTO> allEpacks = epackService.findAll();
        List<FeedbackDTO> allJavaFeedbacks = feedbackService.findAllJavaFeedbacks();
        FeedbackDTO feedback = new FeedbackDTO();
        model.addAttribute("feedback", feedback);
        FeedbackDTO feedbackJava = new FeedbackJava();
        FeedbackDTO feedbackSpring = new FeedbackSpring();
        FeedbackDTO feedbackMicro = new FeedbackMicro();
        model.addAttribute("allEpacks", allEpacks);
        model.addAttribute("feedbackJava", feedbackJava);
        model.addAttribute("feedbackSpring", feedbackSpring);
        model.addAttribute("feedbackMicro", feedbackMicro);
        model.addAttribute("allJavaFeedbacks", allJavaFeedbacks);
        return "allEpacks";
    }

    @PostMapping("/placeOrder")
    public String generateOrder(@NotNull Model model, EpackDTO epack) {
        model.addAttribute("epack", epack);
        return "orderForm";
    }

    @GetMapping("/getJava")
    public String getJava(@NotNull Model model) {
        EpackDTO javaEpack = epackService.findEpackById(1L);
        model.addAttribute("javaEpack", javaEpack);
        return "java";
    }

    @GetMapping("/getSpring")
    public String getSpring(@NotNull Model model) {
        EpackDTO springEpack = epackService.findEpackById(2L);
        model.addAttribute("springEpack", springEpack);
        return "spring";
    }

    @GetMapping("/getMicro")
    public String getMicro(@NotNull Model model) {
        EpackDTO micro = epackService.findEpackById(3L);
        model.addAttribute("micro", micro);
        return "microservices";
    }

    @GetMapping("/getPython")
    public String getPython(@NotNull Model model) {
        EpackDTO python = epackService.findEpackById(4L);
        model.addAttribute("python", python);
        return "python";
    }

    @GetMapping("/getSap")
    public String getSap(@NotNull Model model) {
        EpackDTO sap = epackService.findEpackById(5L);
        model.addAttribute("sap", sap);
        return "sap";
    }

    @GetMapping("/getDevOps")
    public String getDevOps(@NotNull Model model) {
        EpackDTO devops = epackService.findEpackById(6L);
        model.addAttribute("devops", devops);
        return "devops";
    }

}
