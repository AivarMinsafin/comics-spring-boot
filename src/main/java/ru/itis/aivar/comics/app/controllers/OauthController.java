package ru.itis.aivar.comics.app.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.itis.aivar.comics.app.services.OauthService;

@Controller
@Slf4j
public class OauthController {

    @Autowired
    private OauthService oauthService;

    @GetMapping("/oauth/github/callback")
    @PreAuthorize("permitAll()")
    public String oauthGitHub(){
        log.info("Oauth completed fine...");
        return "redirect:/";
    }

}
