package ru.itis.aivar.comics.app.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class OauthServiceException extends RuntimeException{
    public OauthServiceException() {
        super();
    }

    public OauthServiceException(String message) {
        super(message);
    }

    public OauthServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public OauthServiceException(Throwable cause) {
        super(cause);
    }

    protected OauthServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
