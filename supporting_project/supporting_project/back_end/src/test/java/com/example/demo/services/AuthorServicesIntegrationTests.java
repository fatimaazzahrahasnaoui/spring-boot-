package com.example.demo.services;


import com.example.demo.models.dtos.AuthorDto;
import com.example.demo.persistence.AuthorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class AuthorServicesIntegrationTests {

    private final AuthorRepository authorRepository ;
    private final AuthorService authorService ;

    @Autowired
    public AuthorServicesIntegrationTests(AuthorRepository authorRepository, AuthorService authorService) {
        this.authorRepository = authorRepository;
        this.authorService = authorService;
    }


    // initialize the database before each test
    @BeforeEach
    public void setUp() {
        this.authorRepository.deleteAll();
    }

    @Test
    public void testSaveAuthor() {
        // Create an author :
        AuthorDto author = AuthorDto.builder()
                .name("zaynab")
                .age(20)
                .build() ;

        // Save it :
        AuthorDto savedAuthor = authorService.createAuthor(author) ;

        // assertions :
        assertThat(savedAuthor).isNotNull();
        assertThat(savedAuthor.getId()).isNotNull() ;
        assertThat(savedAuthor.getName()).isEqualTo(author.getName()) ;
        assertThat(savedAuthor.getAge()).isEqualTo(author.getAge()) ;
    }

    @Test
    public void testFindAuthorById() {
        // save an author first  :
        AuthorDto author = AuthorDto.builder()
                .name("zaynab")
                .age(20)
                .build() ;

        AuthorDto savedAuthor = authorService.createAuthor(author) ;

        // retrieve the author from the database :
        Optional<AuthorDto> result = authorService.getAuthorById(savedAuthor.getId()) ;
        // assertions :
        assertThat(result).isPresent() ;
        assertThat(result.get().getName()).isEqualTo(author.getName()) ;
        assertThat(result.get().getAge()).isEqualTo(author.getAge()) ;


    }

    @Test
    public void testFindAllAuthors() {
        // save authors :
        AuthorDto author_1 = AuthorDto.builder()
                .name("zaynab")
                .age(20)
                .build() ;

        AuthorDto author_2 = AuthorDto.builder()
                .name("fatima")
                .age(21)
                .build() ;

        AuthorDto savedAuthor_1 = authorService.createAuthor(author_1) ;
        AuthorDto savedAuthor_2 = authorService.createAuthor(author_2) ;

        // retrieve the authors from the database :
        List<AuthorDto> result = authorService.getAllAuthors() ;

        // assertions :
        assertThat(result).hasSize(2) ;
        assertThat(result).contains(savedAuthor_1) ;
        assertThat(result).contains(savedAuthor_2) ;
    }


    @Test
    public void testUpdateAuthor() {

        // create an author and save it :
        AuthorDto author = AuthorDto.builder()
                .name("zaynab")
                .age(20)
                .build() ;
        AuthorDto savedAuthor = authorService.createAuthor(author) ;

        // update the author :
        savedAuthor.setName("fatima");
        AuthorDto updatedAuthor = authorService.updateAuthor(savedAuthor.getId() , savedAuthor) ;

        // assertions :
        assertThat(updatedAuthor).isNotNull() ;
        assertThat(updatedAuthor.getName()).isEqualTo(savedAuthor.getName()) ;
    }

    @Test
    public void deleteAuthor() {

        // create an author and save it :
        AuthorDto author = AuthorDto.builder()
                .name("zaynab")
                .age(20)
                .build() ;
        AuthorDto savedAuthor = authorService.createAuthor(author) ;

        // delete the author :
        authorService.deleteAuthor(savedAuthor.getId());

        // try to retrieve the author :
        Optional<AuthorDto> result = authorService.getAuthorById(savedAuthor.getId()) ;

        // assertions :
        assertThat(result).isNotPresent();
    }

}
