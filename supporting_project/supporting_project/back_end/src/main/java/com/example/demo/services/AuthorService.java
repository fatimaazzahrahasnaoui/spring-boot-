package com.example.demo.services;
import com.example.demo.models.dtos.AuthorDto;
import java.util.List;
import java.util.Optional;

public interface AuthorService {
    AuthorDto createAuthor(AuthorDto authorDto);
    // it's better if find method returns an optional type ---> easier to deal with in the tests
    Optional<AuthorDto> getAuthorById(Long id);
    List<AuthorDto> getAllAuthors();
    AuthorDto updateAuthor(Long id, AuthorDto authorDto);
    Optional<AuthorDto> deleteAuthor(Long id);
}