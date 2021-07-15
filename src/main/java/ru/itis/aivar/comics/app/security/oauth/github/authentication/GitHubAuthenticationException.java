package ru.itis.aivar.comics.app.security.oauth.github.authentication;

import org.springframework.security.core.AuthenticationException;

public class GitHubAuthenticationException extends AuthenticationException {
    public GitHubAuthenticationException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public GitHubAuthenticationException(String msg) {
        super(msg);
    }
}
