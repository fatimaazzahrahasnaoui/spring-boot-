package com.example.demo.models.mappers.impl;

import com.example.demo.models.dtos.AuthorDto;
import com.example.demo.models.entities.AuthorEntity;
import com.example.demo.models.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component // it should be managed by spring ioc too
public class AuthorMapper implements Mapper<AuthorEntity , AuthorDto> {
    // dependency
    private final ModelMapper modelMapper;

    public AuthorMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }


    @Override
    public AuthorEntity mapToEntity(AuthorDto dto) {
        return modelMapper.map(dto , AuthorEntity.class) ;
    }

    @Override
    public AuthorDto mapToDto(AuthorEntity entity) {
        return modelMapper.map(entity , AuthorDto.class);
    }
}
