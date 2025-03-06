package com.example.demo.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class BookDto {

    private Long id;
    private String title;
    private Date publishDate;
    // DTOs carry only DTOs
    private AuthorDto author;
    private CategoryDto categoryDto ;
}

