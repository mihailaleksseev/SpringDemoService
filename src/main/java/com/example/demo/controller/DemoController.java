package com.example.demo.controller;

import com.example.demo.dto.DemoRequestDto;
import com.example.demo.dto.DemoResponseDto;
import com.example.demo.mapper.DemoMapper;
import com.example.demo.service.DemoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/demo", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Demo controller", description = "Demo controller for example")
@RequiredArgsConstructor
public class DemoController {

    private final DemoService demoService;
    private final DemoMapper demoMapper;

    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Demo object created successfully"),
        @ApiResponse(responseCode = "400", description = "Bad request")
    })
    @Operation(summary = "Create demo object")
    @PostMapping
    public ResponseEntity<DemoResponseDto> create(@Valid @RequestBody DemoRequestDto requestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
            demoMapper.toDto(
                demoService.create(
                    demoMapper.fromDto(requestDto)
                )
            )
        );
    }

    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Demo object updated successfully"),
        @ApiResponse(responseCode = "400", description = "Bad request"),
        @ApiResponse(responseCode = "404", description = "Not found")
    })
    @Operation(summary = "Update object by id")
    @PutMapping(("/{id}"))
    public ResponseEntity<DemoResponseDto> update(
        @Valid @RequestBody DemoRequestDto requestDto,
        @PathVariable Long id
    ) {
        return ResponseEntity.ok().body(
            demoMapper.toDto(
                demoService.update(
                    demoMapper.fromDto(requestDto, id)
                )
            )
        );
    }

    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Demo object updated successfully"),
        @ApiResponse(responseCode = "400", description = "Bad request"),
        @ApiResponse(responseCode = "404", description = "Not found")
    })
    @Operation(summary = "Delete demo object by id")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        demoService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Demo object selected successfully"),
        @ApiResponse(responseCode = "400", description = "Bad request"),
        @ApiResponse(responseCode = "404", description = "Not found")
    })
    @Operation(summary = "Get demo object by id")
    @GetMapping("/{id}")
    public ResponseEntity<DemoResponseDto> get(@PathVariable Long id) {
        return ResponseEntity.ok(
            demoMapper.toDto(
                demoService.getById(id)
            )
        );
    }

    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Demo objects list selected successfully"),
        @ApiResponse(responseCode = "400", description = "Bad request"),
        @ApiResponse(responseCode = "404", description = "Not found")
    })
    @Operation(summary = "Get demo objects list")
    @GetMapping()
    public ResponseEntity<List<DemoResponseDto>> getAll() {
        return ResponseEntity.ok(
            demoMapper.toDtoList(
                demoService.getAll()
            )
        );
    }

}
