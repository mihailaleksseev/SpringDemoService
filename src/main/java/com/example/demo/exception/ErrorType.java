package com.example.demo.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

public enum ErrorType {
    NOT_FOUND(HttpStatus.NOT_FOUND.value()),
    BAD_REQUEST(HttpStatus.BAD_REQUEST.value());

    @Getter
    private final int status;

    ErrorType(int status) {
        this.status = status;
    }

}
