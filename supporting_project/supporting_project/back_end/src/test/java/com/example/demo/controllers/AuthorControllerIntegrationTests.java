package com.example.demo.controllers;


import com.example.demo.models.dtos.AuthorDto;
import com.example.demo.services.AuthorService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)

public class AuthorControllerIntegrationTests {

    private final AuthorController authorController ;
    private final MockMvc mockMvc ; // allows us to perform HTTP requests without the need of a web server
    private final ObjectMapper objectMapper ; // mapper object <---> json

    @Autowired
    public AuthorControllerIntegrationTests(AuthorController authorController, MockMvc mockMvc, ObjectMapper objectMapper) {
        this.authorController = authorController;
        this.mockMvc = mockMvc;
        this.objectMapper = objectMapper;
    }

    @Test
    public void testCreate() throws Exception {
        // create an author :
        AuthorDto authorDto = AuthorDto.builder()
                .name("Hala")
                .age(21)
                .build() ;
        // Transform it to JSON format :
        String json = objectMapper.writeValueAsString(authorDto) ;

        // Mock the HTTP request for creation :
        mockMvc.perform(MockMvcRequestBuilders.post("/api/authors/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
        ).andExpect(MockMvcResultMatchers.status().isCreated()
        ).andExpect(MockMvcResultMatchers.jsonPath("$.name").value(authorDto.getName())
        ).andExpect(MockMvcResultMatchers.jsonPath("$.age").value(authorDto.getAge())
        ) ;
    }

    @Test
    public void testFindById() throws Exception {
        // create an author and make it persist :
        AuthorDto authorDto = AuthorDto.builder()
                .name("Chakib")
                .age(21)
                .build() ;

        AuthorDto savedAuthor = authorController.create(authorDto).getBody();

        // mock the HTTP request :
        mockMvc.perform(MockMvcRequestBuilders.get("/api/authors/one/"+savedAuthor.getId())
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk()
        ).andExpect(MockMvcResultMatchers.jsonPath("$.id").value(savedAuthor.getId())
        ).andExpect(MockMvcResultMatchers.jsonPath("$.name").value(savedAuthor.getName())
        ).andExpect(MockMvcResultMatchers.jsonPath("$.age").value(savedAuthor.getAge())
        ) ;
    }

    @Test
    public void testFindAll() throws Exception {
        AuthorDto authorDto_1 = AuthorDto.builder()
                .name("Chakib")
                .age(21)
                .build() ;

        AuthorDto authorDto_2 = AuthorDto.builder()
                .name("Hala")
                .age(21)
                .build() ;

        AuthorDto savedAuthor_1 = authorController.create(authorDto_1).getBody();
        AuthorDto savedAuthor_2 = authorController.create(authorDto_2).getBody();

        // Mock the HTTP request :
        mockMvc.perform(MockMvcRequestBuilders.get("/api/authors/all")
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk()
        ).andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(savedAuthor_1.getId())
        ).andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value(savedAuthor_1.getName())
        ).andExpect(MockMvcResultMatchers.jsonPath("$[0].age").value(savedAuthor_1.getAge())
        );

    }

    @Test
    public void testUpdate() throws Exception {
        // create and save an author :
        AuthorDto authorDto = AuthorDto.builder()
                .name("Chakib")
                .age(21)
                .build() ;
        AuthorDto savedAuthor = authorController.create(authorDto).getBody();
        // update :
        savedAuthor.setName("Hala");
        // convert to JSON format :
        String json = objectMapper.writeValueAsString(savedAuthor) ;

        // Mock HTTP request :
        mockMvc.perform(MockMvcRequestBuilders.put("/api/authors/update/" + savedAuthor.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
        ).andExpect(MockMvcResultMatchers.status().isOk()
        ).andExpect(MockMvcResultMatchers.jsonPath("$.id").value(savedAuthor.getId())
        ).andExpect(MockMvcResultMatchers.jsonPath("$.name").value(savedAuthor.getName())
        ).andExpect(MockMvcResultMatchers.jsonPath("$.age").value(savedAuthor.getAge()));

    }


    @Test
    public void testDelete() throws Exception {

        // create and save an author :
        AuthorDto authorDto = AuthorDto.builder()
                .name("Chakib")
                .age(21)
                .build() ;
        AuthorDto savedAuthor = authorController.create(authorDto).getBody();

        // mock the HTTP request :
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/authors/delete/" + savedAuthor.getId())
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk()
        ).andExpect(MockMvcResultMatchers.jsonPath("$.id").value(savedAuthor.getId())
        ).andExpect(MockMvcResultMatchers.jsonPath("$.name").value(savedAuthor.getName())
        ).andExpect(MockMvcResultMatchers.jsonPath("$.age").value(savedAuthor.getAge())) ;

    }

}
