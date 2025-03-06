package com.example.demo.models.mappers;

public interface Mapper <E , D> {
    public E mapToEntity(D dto) ;
    public D mapToDto(E entity) ;
}
