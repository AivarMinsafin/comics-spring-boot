package ru.itis.aivar.comics.app.controllers.advice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.itis.aivar.comics.app.exceptions.BadRequestException;
import ru.itis.aivar.comics.app.exceptions.IncorrectPasswordException;
import ru.itis.aivar.comics.app.exceptions.NotFoundException;
import ru.itis.aivar.comics.app.exceptions.OauthServiceException;
import ru.itis.aivar.comics.app.utils.images.ImageStorageUtilException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
@Slf4j
public class ExceptionHandlerController {

    private final static String templatesPath = "statuscodes/";

    @ExceptionHandler(NotFoundException.class)
    public String nfe(NotFoundException ex){
        log.warn("Exception {}: {}", ex.getClass().getName(), ex.getLocalizedMessage());
        return getErrorTemplate("e-404");
    }

    @ExceptionHandler(BadRequestException.class)
    public String bre(BadRequestException ex){
        log.warn("Exception {}: {}", ex.getClass().getName(), ex.getLocalizedMessage());
        return getErrorTemplate("e-401");
    }

    @ExceptionHandler(IncorrectPasswordException.class)
    public String ipe(IncorrectPasswordException ex){
        log.warn("Exception {}: {}", ex.getClass().getName(), ex.getLocalizedMessage());
        return getErrorTemplate("e-403");
    }

    @ExceptionHandler(OauthServiceException.class)
    public String ose(OauthServiceException ex){
        log.error("Exception {}: {}", ex.getClass().getName(), ex.getLocalizedMessage());
        return getErrorTemplate("e-500");
    }

    @ExceptionHandler(ImageStorageUtilException.class)
    public String isue(ImageStorageUtilException ex){
        log.error("Exception {}: {}", ex.getClass().getName(), ex.getLocalizedMessage());
        return getErrorTemplate("e-500");
    }

    private static String getErrorTemplate(String s){
        return templatesPath.concat(s);
    }

}
