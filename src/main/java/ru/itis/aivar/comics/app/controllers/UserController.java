package ru.itis.aivar.comics.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.itis.aivar.comics.app.services.UserService;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Controller
public class UserController {

    @Autowired
    private UserService usersService;

    @GetMapping("/add-title-to-list")
    public String addTitleToList(@RequestParam Long id, Principal principal, HttpServletRequest request){
        usersService.addTitleToList(id, principal.getName());
        return "redirect:"+request.getHeader("Referer");
    }

}
