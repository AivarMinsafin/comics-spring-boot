package ru.itis.aivar.comics.app.interceptors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import ru.itis.aivar.comics.app.dto.TitleDto;
import ru.itis.aivar.comics.app.exceptions.BadRequestException;
import ru.itis.aivar.comics.app.models.Title;
import ru.itis.aivar.comics.app.services.AuthorService;
import ru.itis.aivar.comics.app.services.TitleService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class TitleAuthorCheckInterceptor implements HandlerInterceptor {

    @Autowired
    private TitleService titleService;

    private final List<String> checkPaths = Arrays.asList(
            "/title-delete",
            "/title-edit",
            "/chapter-manager",
            "/chapter-add"
    );

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (checkPaths.contains(request.getRequestURI())) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            if (auth == null){
                response.sendError(HttpServletResponse.SC_FORBIDDEN);
                return false;
            }
            String email = auth.getName();
            String param = request.getParameter("id");
            if (param == null || param.isEmpty() || param.isBlank()){
                throw new BadRequestException("Bad request");
            }
            Long titleId = Long.valueOf(param);
            boolean b = titleService.isAuthor(email, titleId);
            if (b){
                return true;
            } else{
                response.sendError(HttpServletResponse.SC_FORBIDDEN);
                return false;
            }
        }
        return true;
    }
}
