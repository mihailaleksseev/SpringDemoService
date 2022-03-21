package com.example.demo.configuration;

import com.example.demo.exception.ErrorMessage;
import com.example.demo.exception.ServiceException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException e, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> message = new ArrayList<>();

        e.getBindingResult().getAllErrors().stream()
            .filter(FieldError.class::isInstance)
            .forEach(ex -> message.add(e.getObjectName() + "." + ((FieldError) ex).getField() + ": " + ex.getDefaultMessage()));

        return toResponseEntity(
            new ErrorMessage(
                HttpStatus.BAD_REQUEST,
                StringUtils.join(message, ", "),
                LocalDateTime.now()
            )
        );
    }

    @ExceptionHandler({ServiceException.class})
    public ResponseEntity<Object> handleServiceException(ServiceException e) {
        return toResponseEntity(
            new ErrorMessage(
                e.getStatus(),
                e.getMessage(),
                LocalDateTime.now()
            )
        );
    }

    private ResponseEntity<Object> toResponseEntity(ErrorMessage errorMessage) {
        if (errorMessage.getStatus() == null) {
            errorMessage.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return ResponseEntity.status(errorMessage.getStatus()).body(errorMessage);
    }

}

