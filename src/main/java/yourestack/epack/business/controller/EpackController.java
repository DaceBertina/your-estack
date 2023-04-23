package yourestack.epack.business.controller;

import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import yourestack.epack.business.domain.EpackDTO;
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
        model.addAttribute("allEpacks", allEpacks);
        return "allEpacks";
    }

    @GetMapping("/java")
    public String getJava(@NotNull Model model) {
        EpackDTO java = epackService.findEpackById(1L);
        model.addAttribute("java", java);
        return "java";
    }

    @GetMapping("/python")
    public String getPython(@NotNull Model model) {
        EpackDTO python = epackService.findEpackById(2L);
        model.addAttribute("python", python);
        return "python";
    }

    @GetMapping("/javascript")
    public String getJavaScript(@NotNull Model model) {
        EpackDTO javascript = epackService.findEpackById(3L);
        model.addAttribute("javascript", javascript);
        return "javascript";
    }

    @GetMapping("/c++")
    public String getCPlus(@NotNull Model model) {
        EpackDTO c = epackService.findEpackById(4L);
        model.addAttribute("c++", c);
        return "c";
    }

    @GetMapping("/sap")
    public String getSap(@NotNull Model model) {
        EpackDTO sap = epackService.findEpackById(5L);
        model.addAttribute("sap", sap);
        return "sap";
    }

    @GetMapping("/devops")
    public String getDevOps(@NotNull Model model) {
        EpackDTO devops = epackService.findEpackById(6L);
        model.addAttribute("devops", devops);
        return "devops";
    }

}
