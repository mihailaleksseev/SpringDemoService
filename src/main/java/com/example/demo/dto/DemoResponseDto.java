package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DemoResponseDto {

    @Schema(description = "Идентификатор")
    private long id;

    @Schema(description = "Имя")
    private String name;

    @Schema(description = "Код")
    private String code;

}
