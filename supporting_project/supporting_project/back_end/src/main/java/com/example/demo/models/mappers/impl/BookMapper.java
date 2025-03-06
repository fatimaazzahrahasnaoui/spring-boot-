package com.example.demo.models.mappers.impl;

import com.example.demo.models.dtos.BookDto;
import com.example.demo.models.entities.AuthorEntity;
import com.example.demo.models.entities.BookEntity;
import com.example.demo.models.entities.CategoryEntity;
import com.example.demo.models.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class BookMapper implements Mapper<BookEntity , BookDto> {
    private final ModelMapper modelMapper;

    public BookMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }


    @Override
    public BookEntity mapToEntity(BookDto dto) {
        return modelMapper.map(dto , BookEntity.class);
    }

    @Override
    public BookDto mapToDto(BookEntity entity) {
        return modelMapper.map(entity  , BookDto.class);
    }
}
