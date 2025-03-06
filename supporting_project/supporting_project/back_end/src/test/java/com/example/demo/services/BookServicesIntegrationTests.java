package com.example.demo.services;

import com.example.demo.models.dtos.AuthorDto;
import com.example.demo.models.dtos.BookDto;
import com.example.demo.models.dtos.CategoryDto;
import com.example.demo.persistence.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class BookServicesIntegrationTests {

    private final BookRepository bookRepository;
    private final BookService bookService;
    @Autowired
    public BookServicesIntegrationTests(BookRepository bookRepository, BookService bookService) {
        this.bookRepository = bookRepository;
        this.bookService = bookService;
    }

    @BeforeEach
    public void setUp() {
        this.bookRepository.deleteAll();
    }

    private Date parseDate(String date) throws Exception {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.parse(date);
    }

    @Test
    public void testSaveBook() throws Exception {
        // first we need an author : can't have a book without an author right ?
        AuthorDto authorDto = AuthorDto.builder()
                .name("Zaynab")
                .age(20)
                .build() ;

        // second we need a category : the category should exist before the book too.
        CategoryDto categoryDto = CategoryDto.builder()
                .label("")
                .build() ;

        Date publishDate = parseDate("2024-10-12");
        BookDto book = BookDto.builder()
                .title("Spring Boot Guide")
                .author(authorDto)
                .categoryDto(categoryDto)
                .publishDate(publishDate)
                .build();

        BookDto savedBook = bookService.createBook(book);

        assertThat(savedBook).isNotNull();
        assertThat(savedBook.getId()).isNotNull();
        assertThat(savedBook.getTitle()).isEqualTo(book.getTitle());
        assertThat(savedBook.getPublishDate()).isEqualTo(book.getPublishDate());
    }

    @Test
    public void testFindBookById() throws Exception {
        // first we need an author : can't have a book without an author right ?
        AuthorDto authorDto = AuthorDto.builder()
                .name("Zaynab")
                .age(20)
                .build() ;

        // second we need a category : the category should exist before the book too.
        CategoryDto categoryDto = CategoryDto.builder()
                .label("")
                .build() ;

        Date publishDate = parseDate("2024-11-20");

        BookDto book = BookDto.builder()
                .title("Spring Boot Guide")
                .author(authorDto)
                .categoryDto(categoryDto)
                .publishDate(publishDate)
                .build();


        BookDto savedBook = bookService.createBook(book);
        Optional<BookDto> result = bookService.getBookById(savedBook.getId());

        assertThat(result).isPresent();
        assertThat(result.get().getTitle()).isEqualTo(book.getTitle());
    }

    @Test
    public void testFindAllBooks() throws Exception {

        AuthorDto authorDto_1 = AuthorDto.builder()
                .name("Zaynab")
                .age(20)
                .build() ;

        AuthorDto authorDto_2 = AuthorDto.builder()
                .name("Zaynab")
                .age(20)
                .build() ;

        CategoryDto categoryDto = CategoryDto.builder()
                .label("")
                .build() ;

        Date publishDate1 = parseDate("2024-11-20");
        Date publishDate2 = parseDate("2024-10-15");

        BookDto book1 = BookDto.builder()
                .title("Spring Boot Guide")
                .author(authorDto_1)
                .categoryDto(categoryDto)
                .publishDate(publishDate1)
                .build();

        BookDto book2 = BookDto.builder()
                .title("Java Basics")
                .author(authorDto_2)
                .categoryDto(categoryDto)
                .publishDate(publishDate2)
                .build();

        bookService.createBook(book1);
        bookService.createBook(book2);

        List<BookDto> result = bookService.getAllBooks();

        assertThat(result).hasSize(2);
        assertThat(result.stream().map(BookDto::getTitle)).contains("Spring Boot Guide", "Java Basics");
    }

    @Test
    public void testUpdateBook() throws Exception {

        AuthorDto authorDto = AuthorDto.builder()
                .name("Zaynab")
                .age(20)
                .build() ;

        CategoryDto categoryDto = CategoryDto.builder()
                .label("")
                .build() ;

        Date publishDate = parseDate("2024-11-20");

        BookDto book = BookDto.builder()
                .title("Spring Boot Guide")
                .author(authorDto)
                .publishDate(publishDate)
                .categoryDto(categoryDto)
                .build();

        BookDto savedBook = bookService.createBook(book);
        savedBook.setTitle("Spring Boot Advanced");
        savedBook.setAuthor(AuthorDto.builder().name("Sami").age(21).build());

        BookDto updatedBook = bookService.updateBook(savedBook.getId(), savedBook);

        assertThat(updatedBook.getTitle()).isEqualTo("Spring Boot Advanced");
    }

    @Test
    public void testDeleteBook() throws Exception {

        AuthorDto authorDto = AuthorDto.builder()
                .name("Zaynab")
                .age(20)
                .build() ;

        CategoryDto categoryDto = CategoryDto.builder()
                .label("")
                .build() ;

        Date publishDate = parseDate("2024-11-20");

        BookDto book = BookDto.builder()
                .title("Spring Boot Guide")
                .author(authorDto)
                .categoryDto(categoryDto)
                .publishDate(publishDate)
                .build();

        BookDto savedBook = bookService.createBook(book);
        bookService.deleteBook(savedBook.getId());

        Optional<BookDto> result = bookService.getBookById(savedBook.getId());

        assertThat(result).isNotPresent();
    }
}
