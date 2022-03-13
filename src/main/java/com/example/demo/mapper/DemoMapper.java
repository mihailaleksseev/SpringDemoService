package com.example.demo.mapper;

import com.example.demo.configuration.MapperConfiguration;
import com.example.demo.dto.DemoRequestDto;
import com.example.demo.dto.DemoResponseDto;
import com.example.demo.model.Demo;
import java.util.List;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(config = MapperConfiguration.class)
public interface DemoMapper {

    Demo fromDto(DemoRequestDto dto);

    Demo fromDto(DemoRequestDto dto, Long id);

    DemoResponseDto toDto(Demo demo);

    List<DemoResponseDto> toDtoList(List<Demo> demos);

}

