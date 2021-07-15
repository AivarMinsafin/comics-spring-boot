package ru.itis.aivar.comics.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import ru.itis.aivar.comics.app.dto.TitleDto;
import ru.itis.aivar.comics.app.dto.forms.TitleCreationForm;
import ru.itis.aivar.comics.app.dto.forms.TitleEditForm;
import ru.itis.aivar.comics.app.services.TitleService;

import javax.validation.Valid;
import java.security.Principal;

@Controller
public class TitleManagerController {

    @Autowired
    private TitleService titleService;

    @GetMapping("/title-manager")
    @PreAuthorize("hasAuthority('ROLE_AUTHOR')")
    public String titleManager(Model model, Principal principal){
        model.addAttribute("titles", titleService.findAllTitlesByAuthorUserEmail(principal.getName()));
        return "title-manager";
    }

    @GetMapping("/title-create")
    @PreAuthorize("hasAuthority('ROLE_AUTHOR')")
    public String createTitle(Model model){
        model.addAttribute("titleCreationForm", TitleCreationForm.builder().build());
        return "title-create";
    }

    @PostMapping("/title-create")
    @PreAuthorize("hasAuthority('ROLE_AUTHOR')")
    public String createTitlePost(@Valid @ModelAttribute("titleCreationForm") TitleCreationForm form, BindingResult bindingResult, Model model, Principal principal){
        if (bindingResult.hasErrors()){
            model.addAttribute("titleCreationForm", form);
            return "title-create";
        }
        form.setEmail(principal.getName());
        titleService.createTitle(form);
        return "redirect:"+ MvcUriComponentsBuilder.fromMappingName("TMC#titleManager").build();
    }

    @GetMapping("/title-edit")
    @PreAuthorize("hasAuthority('ROLE_AUTHOR')")
    public String titleEdit(@RequestParam Long id, Model model){
        TitleDto titleDto = titleService.getTitleById(id);
        model.addAttribute("titleEditForm", TitleEditForm.builder()
                .id(id)
                .name(titleDto.getName())
                .description(titleDto.getDescription())
                .build());
        return "title-edit";
    }

    @PostMapping("/title-edit")
    @PreAuthorize("hasAuthority('ROLE_AUTHOR')")
    public String titleEditPost(@Valid @ModelAttribute("titleEditForm") TitleEditForm form, Model model, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            model.addAttribute("titleEditForm", form);
            return "title-edit";
        }
        titleService.updateTitle(form);
        return "redirect:"+ MvcUriComponentsBuilder.fromMappingName("TMC#titleManager").build();
    }

    @GetMapping("/title-delete")
    @PreAuthorize("hasAuthority('ROLE_AUTHOR')")
    public String deleteTitle(@RequestParam Long id){
        titleService.deleteTitle(id);
        return "redirect:"+ MvcUriComponentsBuilder.fromMappingName("TMC#titleManager").build();
    }
}
