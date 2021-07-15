package ru.itis.aivar.comics.app.controllers;

import org.dom4j.rule.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import ru.itis.aivar.comics.app.dto.ChapterDto;
import ru.itis.aivar.comics.app.dto.forms.ChapterCreationForm;
import ru.itis.aivar.comics.app.dto.forms.ChapterEditForm;
import ru.itis.aivar.comics.app.services.ChapterService;

import javax.validation.Valid;

@Controller
public class ChapterManagerController {

    @Autowired
    private ChapterService chapterService;

    @GetMapping("/chapter-manager")
    @PreAuthorize("hasAuthority('ROLE_AUTHOR')")
    public String chapterManager(@RequestParam Long id, Model model){
        model.addAttribute("chapters", chapterService.findByTitleId(id));
        return "chapter-manager";
    }

    @GetMapping("/chapter-edit")
    @PreAuthorize("hasAuthority('ROLE_AUTHOR')")
    public String chapterEdit(@RequestParam Long id, Model model){
        ChapterDto dto = chapterService.findById(id);
        model.addAttribute("chapterEditForm", ChapterEditForm.builder()
                .id(id)
                .name(dto.getName())
                .build());
        return "chapter-edit";
    }

    @PostMapping("/chapter-edit")
    @PreAuthorize("hasAuthority('ROLE_AUTHOR')")
    public String chapterEditPost(@Valid @ModelAttribute("chapterEditForm") ChapterEditForm form, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()){
            model.addAttribute("chapterEditForm", form);
            return "chapter-edit";
        }
        Long titleId = chapterService.update(form).getTitleId();
        return "redirect:"+ MvcUriComponentsBuilder.fromMappingName("CMC#chapterManager").arg(0, titleId).build();
    }

    @GetMapping("/chapter-add")
    @PreAuthorize("hasAuthority('ROLE_AUTHOR')")
    public String chapterAdd(@RequestParam Long id, Model model){
        model.addAttribute("chapterCreationForm", ChapterCreationForm.builder()
                .id(id)
                .build());
        return "chapter-add";
    }

    @PostMapping("/chapter-add")
    @PreAuthorize("hasAuthority('ROLE_AUTHOR')")
    public String chapterAddPost(@Valid @ModelAttribute("chapterCreationForm") ChapterCreationForm form, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()){
            model.addAttribute("chapterCreationForm", form);
            return "chapter-add";
        }
        chapterService.save(form);
        return "redirect:"+ MvcUriComponentsBuilder.fromMappingName("CMC#chapterManager").arg(0, form.getId()).build();
    }

    @GetMapping("/chapter-delete")
    @PreAuthorize("hasAuthority('ROLE_AUTHOR')")
    public String chapterDelete(@RequestParam Long id){
        Long titleId = chapterService.findById(id).getTitleId();
        chapterService.deleteById(id);
        return "redirect:"+ MvcUriComponentsBuilder.fromMappingName("CMC#chapterManager").arg(0, titleId).build();
    }

}
