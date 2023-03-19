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
import yourestack.epack.business.domain.AddressDTO;
import yourestack.epack.business.model.UserEntity;
import yourestack.epack.business.model.repos.UserRepository;
import yourestack.epack.business.service.impl.AddressServiceImpl;
import yourestack.epack.util.WebUtil;

import java.util.stream.Collectors;


@Controller
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/address")
public class AddressController {

    private final AddressServiceImpl addressService;
    private final UserRepository userRepository;

    @ModelAttribute
    public void prepareContext(final Model model) {
        model.addAttribute("userValues", userRepository.findAll(Sort.by("userId"))
                .stream()
                .collect(Collectors.toMap(UserEntity::getUserId, UserEntity::getFirstName)));
    }

    @GetMapping
    public String list(final Model model) {
        model.addAttribute("address", addressService.findAll());
        return "address/list";
    }

    @GetMapping("/add")
    public String add(@ModelAttribute("address") final AddressDTO addressDTO) {
        return "address/add";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("address") final AddressDTO addressDTO,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "address/add";
        }
        addressService.create(addressDTO);
        redirectAttributes.addFlashAttribute(WebUtil.MSG_SUCCESS, WebUtil.getMessage("address.create.success"));
        return "redirect:/address";
    }

    @GetMapping("/edit/{addressId}")
    public String edit(@PathVariable final Long addressId, final Model model) {
        model.addAttribute("address", addressService.get(addressId));
        return "address/edit";
    }

    @PostMapping("/edit/{addressId}")
    public String edit(@PathVariable final Long addressId,
            @ModelAttribute("address") @Valid final AddressDTO addressDTO,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "address/edit";
        }
        addressService.update(addressId, addressDTO);
        redirectAttributes.addFlashAttribute(WebUtil.MSG_SUCCESS, WebUtil.getMessage("address.update.success"));
        return "redirect:/address";
    }

    @PostMapping("/delete/{addressId}")
    public String delete(@PathVariable final Long addressId,
            final RedirectAttributes redirectAttributes) {
        addressService.delete(addressId);
        redirectAttributes.addFlashAttribute(WebUtil.MSG_INFO, WebUtil.getMessage("address.delete.success"));
        return "redirect:/address";
    }

}
