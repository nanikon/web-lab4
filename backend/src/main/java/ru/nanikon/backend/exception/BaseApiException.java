package ru.nanikon.backend.exception;

import org.springframework.http.HttpStatus;

/**
 * @author Natalia Nikonova
 */
public class BaseApiException extends RuntimeException {
    private final String errorCode;
    private final HttpStatus httpStatus;

    public BaseApiException(String message, String errorCode, HttpStatus httpStatus) {
        super(message);
        this.errorCode = errorCode;
        this.httpStatus = httpStatus;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

}
