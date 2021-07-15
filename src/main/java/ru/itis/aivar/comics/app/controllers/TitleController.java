package ru.itis.aivar.comics.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.itis.aivar.comics.app.services.TitleService;

import java.security.Principal;

@Controller
public class TitleController {

    @Autowired
    private TitleService titleService;

    @GetMapping("/")
    @PreAuthorize("permitAll()")
    public String mainTitlesPage(){
        return "main-titles-page";
    }

    @GetMapping("/title/{id}")
    @PreAuthorize("permitAll()")
    public String titlePage(@PathVariable Long id, Model model, Principal principal){
        model.addAttribute("title", titleService.getTitleById(id));
        model.addAttribute("chapters", titleService.findAllChaptersByTitleId(id));
        model.addAttribute("author", titleService.findAuthorByTitleId(id));
        model.addAttribute("readersCount", titleService.readersCountByTitleId(id));
        if (principal != null){
            model.addAttribute("isReader", titleService.isReader(principal.getName(), id));
        } else {
            model.addAttribute("isReader", true);
        }
        return "title-page";
    }
}
