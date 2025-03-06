package com.example.demo.models.mappers;

public interface Mapper <Entity , Dto>{
    public Entity mapToEntity(Dto dto) ;
    public Dto mapToDto(Entity entity) ;
}
