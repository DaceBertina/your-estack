package yourestack.epack.business.controller;

import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import yourestack.epack.business.domain.EpackDTO;
import yourestack.epack.business.domain.FeedbackDTO;
import yourestack.epack.business.service.impl.EpackServiceImpl;

import java.util.List;

@Log4j2
@Controller
@RequiredArgsConstructor
public class EpackController {

    private final EpackServiceImpl epackService;

    @RequestMapping("/epacks/{id}")
    @ResponseBody
    public EpackDTO findById(Long id) {
        return epackService.findEpackById(id);
    }

    @GetMapping("/allEpacks")
    public String getAllEpacks(@NotNull Model model) {
        List<EpackDTO> allEpacks = epackService.findAll();
        List<EpackDTO> allCourses = epackService.findAll();
        FeedbackDTO feedback = new FeedbackDTO();
        EpackDTO epack = new EpackDTO();
        model.addAttribute("allEpacks", allEpacks);
        model.addAttribute("allCourses", allCourses);
        model.addAttribute("feedback", feedback);
        model.addAttribute("epack", epack);
        return "allEpacks";
    }

    @PostMapping("/placeOrder")
    public String generateOrder(@NotNull Model model, EpackDTO epack) {
        model.addAttribute("epack", epack);
        return "orderForm";
    }

    @GetMapping("/getJava")
    public String getJava(@NotNull Model model) {
        EpackDTO java = epackService.findEpackById(1L);
        model.addAttribute("java", java);
        return "java";
    }

    @GetMapping("/getSpring")
    public String getSpring(@NotNull Model model) {
        EpackDTO spring = epackService.findEpackById(2L);
        model.addAttribute("spring", spring);
        return "spring";
    }

    @GetMapping("/getMicro")
    public String getMicro(@NotNull Model model) {
        EpackDTO micro = epackService.findEpackById(3L);
        model.addAttribute("micro", micro);
        return "micro";
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
