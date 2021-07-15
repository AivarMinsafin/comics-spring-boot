package ru.itis.aivar.comics.app.security.oauth.github.authentication;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class OauthGitHubFilter extends AbstractAuthenticationProcessingFilter {

    private static final AntPathRequestMatcher DEFAULT_ANT_PATH_REQUEST_MATCHER = new AntPathRequestMatcher("/oauth/github/callback", "GET");

    public OauthGitHubFilter() {
        super(DEFAULT_ANT_PATH_REQUEST_MATCHER);
    }

    public OauthGitHubFilter(AuthenticationManager authenticationManager){
        super(DEFAULT_ANT_PATH_REQUEST_MATCHER, authenticationManager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws AuthenticationException, IOException, ServletException {
        GitHubCodeAuthentication authentication = new GitHubCodeAuthentication(getGitHubCode(httpServletRequest));
        return this.getAuthenticationManager().authenticate(authentication);
    }

    private String getGitHubCode(HttpServletRequest httpServletRequest){
        return httpServletRequest.getParameter("code");
    }
}
