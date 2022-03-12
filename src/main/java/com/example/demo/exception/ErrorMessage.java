package com.example.demo.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.Map;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorMessage {

    private ErrorType type;
    private String message;
    private Map<String, Object> details;

}
