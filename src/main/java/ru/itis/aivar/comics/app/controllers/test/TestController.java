package ru.itis.aivar.comics.app.controllers.test;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ru.itis.aivar.comics.app.exceptions.NotFoundException;
import ru.itis.aivar.comics.app.services.AuthorService;

@Controller
public class TestController {

    @Autowired
    private AuthorService authorService;

    @GetMapping("/test")
    public String testAuthorService(){
        return "works";
    }

    @GetMapping("/not-found-test")
    public String notFoundTest(){
        throw new NotFoundException("ew");
    }

    @SneakyThrows
    @GetMapping("/illegal-access")
    public String illegalAccess(){
        throw new IllegalAccessException("ew");
    }
}
