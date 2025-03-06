package com.example.demo.models.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "books")
public class BookEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "publish_date", nullable = false)
    private Date publishDate;

    // Foreign key relationship to AuthorEntity
    @ManyToOne(cascade = CascadeType.PERSIST) // Many books for one author
    @JoinColumn(name = "author_id", nullable = false ) // Foreign key column
    private AuthorEntity author;

    // Foreign key relationship to CategoryEntity
    @ManyToOne(cascade = CascadeType.PERSIST) // Many books for one Category
    @JoinColumn(name = "category_id", nullable = false) // Foreign key column
    private CategoryEntity category;
}
