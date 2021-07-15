package ru.itis.aivar.comics.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import ru.itis.aivar.comics.app.dto.AuthorDto;
import ru.itis.aivar.comics.app.dto.forms.AuthorCreationForm;
import ru.itis.aivar.comics.app.services.AuthorService;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;

@Controller
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    //TODO facade: I don't know how to prevent...

    @GetMapping("/become-author")
    @PreAuthorize("isAuthenticated()")
    public String becomeAuthor(Model model) {
        model.addAttribute(
                "authorCreationForm",
                new AuthorCreationForm()
        );
        return "author-create";
    }

    @PostMapping("/become-author")
    @PreAuthorize("isAuthenticated()")
    public String becomeAuthorPost(@Valid AuthorCreationForm form, BindingResult bindingResult, Model model, Principal principal, HttpServletRequest request) throws ServletException {
        if (bindingResult.hasErrors()){
            model.addAttribute("authorCreationForm", form);
            return "author-create";
        }
        form.setUserId(authorService.findUserByEmail(principal.getName()).getId());
        AuthorDto authorDto = authorService.create(form);
        request.logout();
        return "redirect:"+ MvcUriComponentsBuilder.fromMappingName("AC#authorPageId").arg(0, authorDto.getId()).build();
    }

    @GetMapping("/author-page")
    @PreAuthorize("isAuthenticated()")
    public String authorPage(Model model, Principal principal){
        AuthorDto authorDto = authorService.findAuthorByUserEmail(principal.getName());
        model.addAttribute("author", authorDto);
        model.addAttribute("titles", authorService.findTitlesByAuthor(authorDto));
        model.addAttribute("readersCount", authorService.countOfReadersByAuthorId(authorDto.getId()));
        return "author";
    }

    @GetMapping("/author-page/{id}")
    @PreAuthorize("permitAll()")
    public String authorPageId(@PathVariable Long id, Model model){
        model.addAttribute("author", authorService.findById(id));
        model.addAttribute("titles", authorService.findTitlesByAuthorId(id));
        model.addAttribute("readersCount", authorService.countOfReadersByAuthorId(id));
        return "author";
    }

}
