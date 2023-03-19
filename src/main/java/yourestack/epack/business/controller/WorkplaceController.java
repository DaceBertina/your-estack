package yourestack.epack.business.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import yourestack.epack.business.domain.WorkplaceDTO;
import yourestack.epack.business.model.UserEntity;
import yourestack.epack.business.model.repos.UserRepository;
import yourestack.epack.business.service.impl.WorkplaceServiceImpl;
import yourestack.epack.util.WebUtil;

import java.util.stream.Collectors;


@Controller
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/workplaces")
public class WorkplaceController {

    private final WorkplaceServiceImpl workplaceService;
    private final UserRepository clientRepository;

    @ModelAttribute
    public void prepareContext(final Model model) {
        model.addAttribute("userValues", clientRepository.findAll(Sort.by("userId"))
                .stream()
                .collect(Collectors.toMap(UserEntity::getUserId, UserEntity::getFirstName)));
    }

    @GetMapping
    public String list(final Model model) {
        model.addAttribute("workplaces", workplaceService.findAll());
        return "workplace/list";
    }

    @GetMapping("/add")
    public String add(@ModelAttribute("workplace") final WorkplaceDTO workplaceDTO) {
        return "workplace/add";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("workplace") @Valid final WorkplaceDTO workplaceDTO,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "workplace/add";
        }
        workplaceService.create(workplaceDTO);
        redirectAttributes.addFlashAttribute(WebUtil.MSG_SUCCESS, WebUtil.getMessage("workplace.create.success"));
        return "redirect:/workplaces";
    }

    @GetMapping("/edit/{workplaceId}")
    public String edit(@PathVariable final Long workplaceId, final Model model) {
        model.addAttribute("workplace", workplaceService.get(workplaceId));
        return "workplace/edit";
    }

    @PostMapping("/edit/{workplaceId}")
    public String edit(@PathVariable final Long workplaceId,
            @ModelAttribute("workplace") @Valid final WorkplaceDTO workplaceDTO,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "workplace/edit";
        }
        workplaceService.update(workplaceId, workplaceDTO);
        redirectAttributes.addFlashAttribute(WebUtil.MSG_SUCCESS, WebUtil.getMessage("workplace.update.success"));
        return "redirect:/workplaces";
    }

    @PostMapping("/delete/{workplaceId}")
    public String delete(@PathVariable final Long workplaceId,
            final RedirectAttributes redirectAttributes) {
        workplaceService.delete(workplaceId);
        redirectAttributes.addFlashAttribute(WebUtil.MSG_INFO, WebUtil.getMessage("workplace.delete.success"));
        return "redirect:/workplaces";
    }

}
