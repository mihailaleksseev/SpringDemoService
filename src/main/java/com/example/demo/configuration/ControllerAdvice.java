package com.example.demo.configuration;

import com.example.demo.exception.ErrorMessage;
import com.example.demo.exception.ErrorType;
import com.example.demo.exception.ServiceException;
import com.example.demo.util.LocaleMessageSource;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static java.util.Optional.ofNullable;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class ControllerAdvice {

    //todo добавить обработку других исключений
    private final LocaleMessageSource messages;

    @ExceptionHandler({ServiceException.class})
    public ResponseEntity<ErrorMessage> handleServiceException(ServiceException e) {
        log.error(e.getMessage());
        String message = messages.getMessage(e.getMessage(), e.getParams());
        ErrorMessage errorMessage = new ErrorMessage()
            .setType(e.getType())
            .setMessage(message)
            .setDateTime(LocalDateTime.now());
        return toResponseEntity(errorMessage);
    }

    private ResponseEntity<ErrorMessage> toResponseEntity(ErrorMessage errorMessage) {
        int status = ofNullable(errorMessage.getType())
            .map(ErrorType::getStatus)
            .orElse(HttpStatus.INTERNAL_SERVER_ERROR.value());
        return ResponseEntity.status(status).body(errorMessage);
    }

}
