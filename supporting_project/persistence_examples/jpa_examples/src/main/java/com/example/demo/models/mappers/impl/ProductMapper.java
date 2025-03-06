package com.example.demo.models.mappers.impl;


import com.example.demo.models.dtos.ProductDto;
import com.example.demo.models.entities.ProductEntity;
import com.example.demo.models.mappers.Mapper;
import org.modelmapper.ModelMapper;

public class ProductMapper implements Mapper<ProductEntity , ProductDto> {

    private final ModelMapper modelMapper ;

    public ProductMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public ProductEntity mapToEntity(ProductDto productDto) {
        return modelMapper.map(productDto , ProductEntity.class) ;
    }

    @Override
    public ProductDto mapToDto(ProductEntity productEntity) {
        return modelMapper.map(productEntity , ProductDto.class) ;
    }
}
