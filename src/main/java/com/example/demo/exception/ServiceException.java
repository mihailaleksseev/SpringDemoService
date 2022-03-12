package com.example.demo.exception;

import lombok.Getter;

@Getter
public class ServiceException extends RuntimeException {

    private final ErrorType type;
    private final String message;
    private final Object[] params;

    public ServiceException(ErrorType type, String message, Object... params) {
        super(message);
        this.type = type;
        this.message = message;
        this.params = params;
    }

}
