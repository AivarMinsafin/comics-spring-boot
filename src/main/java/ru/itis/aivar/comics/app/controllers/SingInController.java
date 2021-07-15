package ru.itis.aivar.comics.app.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SingInController {

    @PreAuthorize("permitAll()")
    @GetMapping("/signIn")
    public String getSingInPage() {
        return "sign-in";
    }

}
