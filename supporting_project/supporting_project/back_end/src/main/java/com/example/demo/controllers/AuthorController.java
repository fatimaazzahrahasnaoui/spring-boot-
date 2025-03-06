package com.example.demo.controllers;
import com.example.demo.models.dtos.AuthorDto;
import com.example.demo.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/authors")

public class AuthorController {
    private final AuthorService authorService ;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @PostMapping(path = "/save")
    public ResponseEntity<AuthorDto> create(@RequestBody AuthorDto authorDto) {
        // save the author :
        AuthorDto savedAuthor = authorService.createAuthor(authorDto) ;
        return new ResponseEntity<AuthorDto>(savedAuthor , HttpStatus.CREATED) ;
    }

    @GetMapping(path = "/all")
    public ResponseEntity<List<AuthorDto>> findAll() {
        List<AuthorDto> authorDtos = authorService.getAllAuthors() ;
        return new ResponseEntity<List<AuthorDto>>(authorDtos , HttpStatus.OK) ;
    }

    @GetMapping(path = "/one/{id}")
    public ResponseEntity<AuthorDto> findByID(@PathVariable("id") Long id) {
        return authorService.getAuthorById(id).
                map(authorDto -> new ResponseEntity<AuthorDto>(authorDto , HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<AuthorDto>(HttpStatus.NOT_FOUND)) ;
    }

    @PutMapping(path = "/update/{id}")
    public ResponseEntity<AuthorDto> update(@PathVariable("id") Long id, @RequestBody AuthorDto authorDto) {
        AuthorDto updatedAuthor = authorService.updateAuthor(id , authorDto) ;
        return new ResponseEntity<AuthorDto>(updatedAuthor , HttpStatus.OK) ;

    }

    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<AuthorDto> delete(@PathVariable("id") Long id) {
        return authorService.deleteAuthor(id)
                .map(authorDto -> new ResponseEntity<AuthorDto>(authorDto , HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<AuthorDto>(HttpStatus.NOT_FOUND)) ;

    }

}
