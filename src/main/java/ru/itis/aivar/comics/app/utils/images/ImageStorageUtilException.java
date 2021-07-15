package ru.itis.aivar.comics.app.utils.images;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class ImageStorageUtilException extends RuntimeException{
    public ImageStorageUtilException() {
        super();
    }

    public ImageStorageUtilException(String message) {
        super(message);
    }

    public ImageStorageUtilException(String message, Throwable cause) {
        super(message, cause);
    }

    public ImageStorageUtilException(Throwable cause) {
        super(cause);
    }

    protected ImageStorageUtilException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
