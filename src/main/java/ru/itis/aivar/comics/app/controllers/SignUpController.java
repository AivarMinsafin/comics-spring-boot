package ru.itis.aivar.comics.app.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import ru.itis.aivar.comics.app.dto.forms.UserSignUpForm;
import ru.itis.aivar.comics.app.services.SignUpService;

import javax.validation.Valid;
import java.util.Objects;

@Controller
public class SignUpController {

    private SignUpService signUpService;

    public SignUpController(SignUpService signUpService) {
        this.signUpService = signUpService;
    }

    @PreAuthorize("permitAll()")
    @GetMapping("/signUp")
    public String getSignUpPage(Model model){
        model.addAttribute("userSignUpForm", new UserSignUpForm());
        return "sign-up";
    }

    @PreAuthorize("permitAll()")
    @PostMapping("/signUp")
    public String signUp(@Valid UserSignUpForm form, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()){
            bindingResult.getAllErrors().stream().anyMatch(error ->{
                if (Objects.requireNonNull(error.getCodes())[0].equals("userSignUpForm.TwoFieldsMatch")){
                    model.addAttribute("passwordsErrorMessage", error.getDefaultMessage());
                }
                return true;
            });
            model.addAttribute("userSignUpForm", form);
            return "sign-up";
        }
        signUpService.signUp(form);
        return "redirect:"+ MvcUriComponentsBuilder.fromMappingName("SIC#getSingInPage").build();
    }
}
