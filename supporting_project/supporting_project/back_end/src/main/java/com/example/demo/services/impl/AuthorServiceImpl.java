package com.example.demo.services.impl;

import com.example.demo.models.dtos.AuthorDto;
import com.example.demo.models.entities.AuthorEntity;
import com.example.demo.models.mappers.impl.AuthorMapper;
import com.example.demo.persistence.AuthorRepository;
import com.example.demo.services.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;

    public AuthorServiceImpl(AuthorRepository authorRepository, AuthorMapper authorMapper) {
        this.authorRepository = authorRepository;
        this.authorMapper = authorMapper;
    }

    @Override
    public AuthorDto createAuthor(AuthorDto authorDto) {
        //
        AuthorEntity authorEntity = authorMapper.mapToEntity(authorDto);
        //
        AuthorEntity savedAuthor = authorRepository.save(authorEntity);
        return authorMapper.mapToDto(savedAuthor);
    }

    @Override
    // minor change in this method
    public Optional<AuthorDto> getAuthorById(Long id) {
        Optional<AuthorDto> authorDto = authorRepository.findById(id).map(authorMapper::mapToDto) ;
        return authorDto ;

    }

    @Override
    public List<AuthorDto> getAllAuthors() {
        List<AuthorEntity> authors = (List<AuthorEntity>) authorRepository.findAll();
        return authors.stream().map(authorMapper::mapToDto).collect(Collectors.toList());
    }

    @Override
    public AuthorDto updateAuthor(Long id, AuthorDto authorDto) {
        AuthorEntity authorEntity = authorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Author not found"));

        authorEntity.setName(authorDto.getName());
        authorEntity.setAge(authorDto.getAge());
        AuthorEntity updatedAuthor = authorRepository.save(authorEntity);

        return authorMapper.mapToDto(updatedAuthor);
    }

    @Override
    public Optional<AuthorDto> deleteAuthor(Long id) {
        // try to retrieve the author from the database :
        Optional<AuthorDto> result = getAuthorById(id) ;
        authorRepository.deleteById(id);
        return result ;
    }

}
