package com.example.demo.controllers;


import com.example.demo.models.dtos.AuthorDto;
import com.example.demo.models.dtos.BookDto;
import com.example.demo.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Book;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }
    @PostMapping(path = "/save")
    public ResponseEntity<BookDto> create(@RequestBody BookDto BookDto) {
        BookDto booksaved = bookService.createBook(BookDto);
        return new ResponseEntity<BookDto>(booksaved, HttpStatus.CREATED);
    }

    @GetMapping(path = "/all")
    public ResponseEntity<List<BookDto>> getAll() {
        List<BookDto> books = bookService.getAllBooks();
        return new ResponseEntity<List<BookDto>>(books, HttpStatus.OK);
    }

    @GetMapping(path = "/one/{id}")
    public ResponseEntity<BookDto> getOne(@PathVariable Long id) {
        Optional<BookDto> book = bookService.getBookById(id);

        return book.map(bookDto -> new ResponseEntity<BookDto>(bookDto, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<BookDto>(HttpStatus.NOT_FOUND));
    }

    @PutMapping(path = "/update/{id}")
    public ResponseEntity<BookDto> update(@PathVariable Long id, @RequestBody BookDto BookDto) {
        BookDto book_updated = bookService.updateBook(id, BookDto);
        return new ResponseEntity<BookDto>(book_updated, HttpStatus.OK);
    }

    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<BookDto> delete(@PathVariable Long id) {
        Optional<BookDto> deletedBook = bookService.deleteBook(id);

        return deletedBook.map(bookDto -> new ResponseEntity<BookDto>(bookDto , HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<BookDto>(HttpStatus.NOT_FOUND));
    }
}




