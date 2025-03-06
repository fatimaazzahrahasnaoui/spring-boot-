package com.example.demo.controllers;

import com.example.demo.models.dtos.AuthorDto;
import com.example.demo.models.dtos.BookDto;
import com.example.demo.models.dtos.CategoryDto;
import com.example.demo.services.BookService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import java.time.Instant;
import java.util.Date;
import java.text.SimpleDateFormat;

import static com.example.demo.models.dtos.AuthorDto.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)

public class BookControllerIntegrationTests {
    private final BookController bookController;
    private final MockMvc mockMvc;
    private final ObjectMapper objectMapper;

    @Autowired
    public BookControllerIntegrationTests(BookService bookService, MockMvc mockMvc, ObjectMapper objectMapper, BookController bookController) {
        this.mockMvc = mockMvc;
        this.bookController = bookController;
        this.objectMapper = objectMapper;
    }

    private Date parseDate(String date) throws Exception {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.parse(date);
    }

    @Test
    public void testCreateBook() throws Exception {
        // Create a book
        AuthorDto authorDto = AuthorDto.builder()
                .name("Zaynab")
                .age(20)
                .build();

        CategoryDto categoryDto = CategoryDto.builder()
                .label("Info")
                .build();
        Date publishDate = parseDate("2024-11-20");

        BookDto book = BookDto.builder()
                .title("Java")
                .author(authorDto)
                .categoryDto(categoryDto)
                .publishDate(publishDate)
                .build();

        // Convert book to JSON
        String bookJson = objectMapper.writeValueAsString(book);

        // Perform POST request and capture the result
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/api/books/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(bookJson)
                ).andExpect(MockMvcResultMatchers.status().isCreated()
                ).andExpect(MockMvcResultMatchers.jsonPath("$.title").value(book.getTitle())
                ).andExpect(MockMvcResultMatchers.jsonPath("$.author.name").value(book.getAuthor().getName()) // Compare name
                ).andExpect(MockMvcResultMatchers.jsonPath("$.author.age").value(book.getAuthor().getAge())   // Compare age
                ).andExpect(MockMvcResultMatchers.jsonPath("$.categoryDto.label").value(book.getCategoryDto().getLabel()) // Compare category label
                ).andReturn();

        //Test The publishdate

        // Extract the JSON response body
        String responseBody = result.getResponse().getContentAsString();

        // Extract the publish date from the JSON response
        // Parse the expected and actual dates as Instant objects for comparison
        // Use AssertJ to compare parsed dates
        assertThat(Instant.parse(JsonPath.read(responseBody, "$.publishDate"))).isEqualTo(book.getPublishDate().toInstant()
        );
    }

    @Test

    public void testFindBookById() throws Exception {
        // create and save a book
        AuthorDto authorDto = AuthorDto.builder()
                .name("Sami")
                .age(21)
                .build();

        CategoryDto categoryDto = CategoryDto.builder()
                .label("Sport")
                .build();
        Date publishDate = parseDate("2024-07-29");

        BookDto book = BookDto.builder()
                .title("BarcaIsLove")
                .author(authorDto)
                .categoryDto(categoryDto)
                .publishDate(publishDate)
                .build();

        // creation du book afin de le comparer avec celle request et celle response
        BookDto savedbook = bookController.create(book).getBody();

        // mock the HTTP request :
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/api/books/one/" + savedbook.getId())
        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk()
        ).andExpect(MockMvcResultMatchers.jsonPath("$.id").value(savedbook.getId())
        ).andExpect(MockMvcResultMatchers.jsonPath("$.title").value(savedbook.getTitle())
        ).andExpect(MockMvcResultMatchers.jsonPath("$.author.name").value(savedbook.getAuthor().getName())
        ).andExpect(MockMvcResultMatchers.jsonPath("$.author.age").value(savedbook.getAuthor().getAge())
        ).andExpect(MockMvcResultMatchers.jsonPath("$.categoryDto.label").value(savedbook.getCategoryDto().getLabel())
        ).andReturn();


        //Test The publishdate

        // Extract the JSON response body
        String responseBody = result.getResponse().getContentAsString();

        // Extract the publish date from the JSON response
        // Parse the expected and actual dates as Instant objects for comparison
        // Use AssertJ to compare parsed dates
        assertThat(Instant.parse(JsonPath.read(responseBody, "$.publishDate"))).isEqualTo(savedbook.getPublishDate().toInstant()
        );

    }

    @Test
    public void testFindAllBooks() throws Exception {
        // create and save book(s)
        AuthorDto authorDto_1 = AuthorDto.builder()
                .name("Hala")
                .age(21)
                .build();
        AuthorDto authorDto_2 = AuthorDto.builder()
                .name("Fatima")
                .age(30)
                .build();

        CategoryDto categoryDto_1 = CategoryDto.builder()
                .label("Astronomy")
                .build();
        CategoryDto categoryDto_2 = CategoryDto.builder()
                .label("Mechanics")
                .build();

        Date publishDate_1 = parseDate("2024-12-18");
        Date publishDate_2 = parseDate("2024-02-05");

        BookDto book_1 = BookDto.builder()
                .title("Welcome To Jupiter")
                .author(authorDto_1)
                .categoryDto(categoryDto_1)
                .publishDate(publishDate_1)
                .build();
        BookDto book_2 = BookDto.builder()
                .title("Loi de Gauss")
                .author(authorDto_2)
                .categoryDto(categoryDto_2)
                .publishDate(publishDate_2)
                .build();

        // creation du book afin de le comparer avec celle request et celle response
        BookDto savedbook_1 = bookController.create(book_1).getBody();
        BookDto savedbook_2 = bookController.create(book_2).getBody();

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/api/books/all")
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk()
        ).andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(savedbook_1.getId())
        ).andExpect(MockMvcResultMatchers.jsonPath("$[0].title").value(savedbook_1.getTitle())
        ).andExpect(MockMvcResultMatchers.jsonPath("$[0].author.name").value(savedbook_1.getAuthor().getName())
        ).andExpect(MockMvcResultMatchers.jsonPath("$[0].author.age").value(savedbook_1.getAuthor().getAge())
        ).andExpect(MockMvcResultMatchers.jsonPath("$[0].categoryDto.label").value(savedbook_1.getCategoryDto().getLabel())
        ).andExpectAll(MockMvcResultMatchers.jsonPath("$[1].id").value(savedbook_2.getId())
        ).andReturn();

        String responseBody = result.getResponse().getContentAsString();
        assertThat(Instant.parse(JsonPath.read(responseBody,"$[0].publishDate"))).isEqualTo(savedbook_1.getPublishDate().toInstant());


    }

    @Test
    public void testUpdateBook() throws Exception {
        // create and save a book
        AuthorDto authorDto = AuthorDto.builder()
                .name("Ayoub")
                .age(21)
                .build();

        CategoryDto categoryDto = CategoryDto.builder()
                .label("Sport")
                .build();
        Date publishDate = parseDate("2024-06-17");

        BookDto book = BookDto.builder()
                .title("Motivation everywhere")
                .author(authorDto)
                .categoryDto(categoryDto)
                .publishDate(publishDate)
                .build();

        BookDto savedbook = bookController.create(book).getBody();

        // update :
        savedbook.setTitle("The World cup");
        savedbook.setPublishDate(parseDate("2024-11-04"));
        savedbook.setAuthor(AuthorDto.builder().name("Sami").age(21).build());

        // convert to JSON format :
        String updatedBookJson = objectMapper.writeValueAsString(savedbook);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.put("/api/books/update/" + savedbook.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(updatedBookJson)
        ).andExpect(MockMvcResultMatchers.status().isOk()
        ).andExpect(MockMvcResultMatchers.jsonPath("$.id").value(savedbook.getId())
        ).andExpect(MockMvcResultMatchers.jsonPath("$.title").value(savedbook.getTitle())
        ).andExpect(MockMvcResultMatchers.jsonPath("$.author.name").value(savedbook.getAuthor().getName())
        ).andExpect(MockMvcResultMatchers.jsonPath("$.author.age").value(savedbook.getAuthor().getAge())
        ).andExpect(MockMvcResultMatchers.jsonPath("$.categoryDto.label").value(savedbook.getCategoryDto().getLabel())
        ).andReturn();

        String ResponseBody = result.getResponse().getContentAsString();
        assertThat(Instant.parse(JsonPath.read(ResponseBody,"$.publishDate"))).isEqualTo(savedbook.getPublishDate().toInstant());

    }

    @Test
    public void testDeleteBook() throws Exception {
        // create and save a book
        AuthorDto authorDto = AuthorDto.builder()
                .name("Bilal")
                .age(21)
                .build();

        CategoryDto categoryDto = CategoryDto.builder()
                .label("Philosophy")
                .build();
        Date publishDate = parseDate("2024-06-17");

        BookDto book = BookDto.builder()
                .title("Particles are everywhere")
                .author(authorDto)
                .categoryDto(categoryDto)
                .publishDate(publishDate)
                .build();

        BookDto savedBook = bookController.create(book).getBody();

        MvcResult result =mockMvc.perform(MockMvcRequestBuilders.delete("/api/books/delete/" + savedBook.getId()
        ).contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk()
        ).andExpect(MockMvcResultMatchers.jsonPath("$.id").value(savedBook.getId())
        ).andExpect(MockMvcResultMatchers.jsonPath("$.title").value(savedBook.getTitle())
        ).andExpect(MockMvcResultMatchers.jsonPath("$.author.name").value(savedBook.getAuthor().getName())
        ).andExpect(MockMvcResultMatchers.jsonPath("$.author.age").value(savedBook.getAuthor().getAge())
        ).andExpect(MockMvcResultMatchers.jsonPath("$.categoryDto.label").value(savedBook.getCategoryDto().getLabel())
        ).andReturn();

        String ResponseBody = result.getResponse().getContentAsString();
        assertThat(Instant.parse(JsonPath.read(ResponseBody,"$.publishDate"))).isEqualTo(savedBook.getPublishDate().toInstant());

    }

}