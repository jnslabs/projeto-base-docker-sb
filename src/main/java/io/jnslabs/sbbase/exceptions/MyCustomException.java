package io.jnslabs.sbbase.exceptions;

import org.springframework.http.HttpStatus;

/**
 * @Autor Jairo Nascimento
 * @Created 03/03/2022 - 16:32
 */
public class MyCustomException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private final String message;
    private final HttpStatus httpStatus;

    public MyCustomException(String message, HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
