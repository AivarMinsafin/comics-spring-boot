package ru.itis.aivar.comics.app.controllers.advice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
public class StatusCodeController implements ErrorController {

    private final static String templatesPath = "statuscodes/";

    @GetMapping("/error")
    public String handleError(HttpServletRequest request){
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        if (status != null){
            int statusCode = Integer.parseInt(status.toString());
            if (statusCode == HttpStatus.NOT_FOUND.value()) return getErrorTemplate("e-404");
            if (statusCode == HttpStatus.FORBIDDEN.value()) return getErrorTemplate("e-403");
            if (statusCode == HttpStatus.BAD_REQUEST.value()) return getErrorTemplate("e-400");
            if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) return getErrorTemplate("e-500");
        }
        return getErrorTemplate("e");
    }

    private static String getErrorTemplate(String s){
        return templatesPath.concat(s);
    }

    @Override
    public String getErrorPath() {
        return null;
    }
}
