package ru.itis.aivar.comics.app.interceptors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import ru.itis.aivar.comics.app.exceptions.BadRequestException;
import ru.itis.aivar.comics.app.services.ChapterService;
import ru.itis.aivar.comics.app.services.TitleService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

@Component
public class ChapterAuthorCheckInterceptor implements HandlerInterceptor {

    @Autowired
    private TitleService titleService;
    @Autowired
    private ChapterService chapterService;

    private final List<String> checkPaths = Arrays.asList(
            "/chapter-edit",
            "/chapter-delete"
    );

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (checkPaths.contains(request.getRequestURI())) {
            String email = SecurityContextHolder.getContext().getAuthentication().getName();
            String param = request.getParameter("id");
            if (param == null || param.isEmpty() || param.isBlank()){
                throw new BadRequestException("Bad request");
            }
            Long chapterId = Long.valueOf(param);
            Long titleId = chapterService.findById(chapterId).getTitleId();
            boolean b = titleService.isAuthor(email, titleId);
            if (b) {
                return true;
            } else {
                response.sendError(HttpServletResponse.SC_FORBIDDEN);
                return false;
            }
        }
        return true;
    }
}
