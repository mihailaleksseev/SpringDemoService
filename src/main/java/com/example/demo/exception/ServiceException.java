package com.example.demo.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ServiceException extends RuntimeException {

    private final HttpStatus status;
    private final String message;
    private final Object[] params;

    public ServiceException(HttpStatus status, String message, Object... params) {
        super(message);
        this.status = status;
        this.message = message;
        this.params = params;
    }

}
