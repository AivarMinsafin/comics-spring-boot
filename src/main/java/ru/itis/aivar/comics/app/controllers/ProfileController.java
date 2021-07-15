package ru.itis.aivar.comics.app.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import ru.itis.aivar.comics.app.dto.UserDto;
import ru.itis.aivar.comics.app.dto.forms.UserEditForm;
import ru.itis.aivar.comics.app.services.UserService;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@Slf4j
public class ProfileController {

    @Autowired
    private UserService userService;

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/profile")
    public String getProfile(Principal principal, Model model){
        log.info("User: {} is entering profile...", principal.getName());
        model.addAttribute("user", userService.findByEmail(principal.getName()));
        model.addAttribute("titles", userService.findUserTitles(principal.getName()));
        return "profile";
    }

    @GetMapping("/profile-edit")
    @PreAuthorize("isAuthenticated()")
    public String getProfileEditPage(Principal principal, Model model){
        UserDto userDto = userService.findByEmail(principal.getName());
        model.addAttribute("userEditForm", UserEditForm.builder()
                .newEmail(userDto.getEmail())
                .newUsername(userDto.getUsername())
                .build());
        return "profile-edit";
    }

    @PostMapping("/profile-edit")
    @PreAuthorize("isAuthenticated()")
    public String profileEdit(@Valid @ModelAttribute("userEditForm") UserEditForm form, BindingResult bindingResult, Model model, Principal principal){
        if (bindingResult.hasErrors()){
            model.addAttribute("userEditForm", form);
            return "profile-edit";
        }
        form.setId(userService.findByEmail(principal.getName()).getId());
        Object obj = userService.update(form);
        if (obj == null){
            model.addAttribute("passwordsErrorMessage", "Wrong password");
            return "profile-edit";
        }
        return "redirect:"+ MvcUriComponentsBuilder.fromMappingName("PC#getProfile").build();
    }
}
