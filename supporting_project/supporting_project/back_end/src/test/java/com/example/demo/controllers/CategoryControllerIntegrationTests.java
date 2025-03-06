package com.example.demo.controllers;

import com.example.demo.models.dtos.AuthorDto;
import com.example.demo.models.dtos.CategoryDto;
import com.example.demo.services.AuthorService;
import com.example.demo.services.CategoryService;
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

public class CategoryControllerIntegrationTests {

    private final CategoryController categoryController; // the actual controller
    private final MockMvc mockMvc ; // allows us to perform HTTP requests without the need of a web server
    private final ObjectMapper objectMapper ; // mapper object <---> json

    @Autowired
    public CategoryControllerIntegrationTests(CategoryService categoryService, CategoryController categoryController, MockMvc mockMvc, ObjectMapper objectMapper) {
        this.categoryController = categoryController;
        this.mockMvc = mockMvc;
        this.objectMapper = objectMapper;
    }


    @Test
    public void testCreate() throws Exception {
        // create a category :
        CategoryDto categoryDto = CategoryDto.builder()
                .label("sami")
                .build() ;
        // Transform it to JSON format :
        String json = objectMapper.writeValueAsString(categoryDto) ;

        // Mock the HTTP request for creation :
        mockMvc.perform(MockMvcRequestBuilders.post("/api/category/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
        ).andExpect(MockMvcResultMatchers.status().isCreated()
        ).andExpect(MockMvcResultMatchers.jsonPath("$.label").value(categoryDto.getLabel())
        ) ;
    }

    @Test
    public void testFindById() throws Exception {
        // create a category and make it persist :
        CategoryDto categoryDto = CategoryDto.builder()
                .label("hala")
                .build() ;

        CategoryDto savedCategory = categoryController.createCategory(categoryDto).getBody() ;

        // mock the HTTP request :
        mockMvc.perform(MockMvcRequestBuilders.get("/api/category/one/"+savedCategory.getId())
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk()
        ).andExpect(MockMvcResultMatchers.jsonPath("$.id").value(savedCategory.getId())
        ).andExpect(MockMvcResultMatchers.jsonPath("$.label").value(savedCategory.getLabel())
        ) ;
    }

    @Test
    public void testFindAll() throws Exception {
        CategoryDto categoryDto_1 = CategoryDto.builder()
                .label("hala")
                .build() ;

        CategoryDto categoryDto_2 = CategoryDto.builder()
                .label("sami")
                .build() ;

        CategoryDto savedCategory_1 = categoryController.createCategory(categoryDto_1).getBody() ;
        CategoryDto savedCategory_2 = categoryController.createCategory(categoryDto_2).getBody() ;

        // Mock the HTTP request :
        mockMvc.perform(MockMvcRequestBuilders.get("/api/category/all")
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk()
        ).andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(savedCategory_1.getId())
        ).andExpect(MockMvcResultMatchers.jsonPath("$[0].label").value(savedCategory_1.getLabel())
        );
    }

    @Test
    public void testUpdate() throws Exception {
        // create and save a category :
        CategoryDto categoryDto = CategoryDto.builder()
                .label("arawkan")
                .build() ;
        CategoryDto savedCategory = categoryController.createCategory(categoryDto).getBody() ;
        // update :
        categoryDto.setLabel("Hala");
        // convert to JSON format :
        String json = objectMapper.writeValueAsString(savedCategory) ;

        // Mock HTTP request :
        mockMvc.perform(MockMvcRequestBuilders.put("/api/category/update/" + savedCategory.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
        ).andExpect(MockMvcResultMatchers.status().isOk()
        ).andExpect(MockMvcResultMatchers.jsonPath("$.id").value(savedCategory.getId())
        ).andExpect(MockMvcResultMatchers.jsonPath("$.label").value(savedCategory.getLabel())
        );
    }


    @Test
    public void testDelete() throws Exception {

        // create and save a catogory :
        CategoryDto categoryDto = CategoryDto.builder()
                .label("hala")
                .build();
        CategoryDto savedCategory = categoryController.createCategory(categoryDto).getBody();

        // mock the HTTP request :
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/category/delete/" + savedCategory.getId())
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk()
        ).andExpect(MockMvcResultMatchers.jsonPath("$.id").value(savedCategory.getId())
        ).andExpect(MockMvcResultMatchers.jsonPath("$.label").value(savedCategory.getLabel())
        );
    }
}