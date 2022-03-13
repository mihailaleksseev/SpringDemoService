package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DemoResponseDto {

    @Schema(description = "Id")
    private long id;

    @Schema(description = "Name")
    private String name;

    @Schema(description = "Cod")
    private String code;

}
