package com.example.demo.models.mappers.impl;

import com.example.demo.models.dtos.CategoryDto;
import com.example.demo.models.entities.CategoryEntity;
import com.example.demo.models.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper implements Mapper<CategoryEntity , CategoryDto> {
    private final ModelMapper modelMapper ;

    public CategoryMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }


    @Override
    public CategoryEntity mapToEntity(CategoryDto dto) {
        return modelMapper.map(dto , CategoryEntity.class);
    }

    @Override
    public CategoryDto mapToDto(CategoryEntity entity) {
        return modelMapper.map(entity , CategoryDto.class);
    }
}
