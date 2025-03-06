package com.example.demo.services.impl;

import com.example.demo.models.dtos.BookDto;
import com.example.demo.models.entities.AuthorEntity;
import com.example.demo.models.entities.BookEntity;
import com.example.demo.models.entities.CategoryEntity;
import com.example.demo.models.mappers.impl.BookMapper;
import com.example.demo.persistence.AuthorRepository;
import com.example.demo.persistence.BookRepository;
import com.example.demo.persistence.CategoryRepository;
import com.example.demo.services.BookService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final CategoryRepository categoryRepository;
    private final BookMapper bookMapper;

    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository, CategoryRepository categoryRepository, BookMapper bookMapper) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.categoryRepository = categoryRepository;
        this.bookMapper = bookMapper;
    }


    @Override
    public BookDto createBook(BookDto bookDto) {
        BookEntity bookEntity = bookMapper.mapToEntity(bookDto);
        BookEntity savedBook = bookRepository.save(bookEntity);
        return bookMapper.mapToDto(savedBook);
    }

    // @Override
    // public BookDto getBookById(Long id) {
    //     BookEntity bookEntity = bookRepository.findById(id)
    //             .orElseThrow(() -> new RuntimeException("Book not found"));
    //     return bookMapper.mapToDto(bookEntity);
    // }
    @Override
    public Optional<BookDto> getBookById(Long id) {
        return bookRepository.findById(id).map(bookMapper::mapToDto);
    }

    @Override
    public List<BookDto> getAllBooks() {
        List<BookEntity> books = (List<BookEntity>) bookRepository.findAll();
        return books.stream().map(bookMapper::mapToDto).collect(Collectors.toList());
    }

    @Override
    public BookDto updateBook(Long id, BookDto bookDto) {
        BookEntity bookEntity = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        // Update simple fields
        bookEntity.setTitle(bookDto.getTitle());
        bookEntity.setPublishDate(bookDto.getPublishDate());

        // Update nested Author
        if (bookDto.getAuthor() != null) {
            AuthorEntity authorEntity = authorRepository.findById(bookEntity.getAuthor().getId())
                    .orElseThrow(() -> new RuntimeException("Author not found"));
            authorEntity.setName(bookDto.getAuthor().getName());
            authorEntity.setAge(bookDto.getAuthor().getAge());
            bookEntity.setAuthor(authorEntity); // Update the book's author reference
        }

        // Update nested Category
        if (bookDto.getCategoryDto() != null) {
            CategoryEntity categoryEntity = categoryRepository.findById(bookDto.getCategoryDto().getId())
                    .orElseThrow(() -> new RuntimeException("Category not found"));
            categoryEntity.setLabel(bookDto.getCategoryDto().getLabel());
            bookEntity.setCategory(categoryEntity); // Update the book's category reference
        }

        // Save updated entity
        BookEntity updatedBook = bookRepository.save(bookEntity);

        // Map back to DTO and return
        return bookMapper.mapToDto(updatedBook);
    }


    @Override
    public Optional<BookDto> deleteBook(Long id) {
        Optional<BookDto> result = getBookById(id) ;
        bookRepository.deleteById(id);
        return result ;
    }
}
