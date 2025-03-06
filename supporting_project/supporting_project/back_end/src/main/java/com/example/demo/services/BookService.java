package com.example.demo.services;

import com.example.demo.models.dtos.BookDto;
import java.util.List;
import java.util.Optional;

public interface BookService {
    BookDto createBook(BookDto bookDto);
    Optional<BookDto> getBookById(Long id);
    List<BookDto> getAllBooks();
    BookDto updateBook(Long id, BookDto bookDto);
    Optional<BookDto> deleteBook(Long id);
}
