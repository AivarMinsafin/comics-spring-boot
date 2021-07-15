package ru.itis.aivar.comics.app.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component
public class AppExceptionResolver extends SimpleMappingExceptionResolver {

    public AppExceptionResolver() {
        setWarnLogCategory(AppExceptionResolver.class.getName());
        setDefaultErrorView("statuscodes/e");
    }

    @Override
    protected String buildLogMessage(Exception ex, HttpServletRequest request) {
        return "Exception: " + ex.getLocalizedMessage();
    }

    @Override
    protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        log.error("Exception {}: {}", ex.getClass().getName(), ex.getLocalizedMessage());
        return super.doResolveException(request, response, handler, ex);
    }
}
