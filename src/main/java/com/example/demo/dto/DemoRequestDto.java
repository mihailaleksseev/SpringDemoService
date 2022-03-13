package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class DemoRequestDto {

    @NotEmpty
    @Schema(description = "Name")
    private String name;

    @NotEmpty
    @Size(min = 10, max = 12)
    @Schema(example = "5412-99-1666",description = "Code")
    private String code;

}
