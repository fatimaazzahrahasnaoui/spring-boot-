package com.example.demo.persistence;

import com.example.demo.models.entities.BookEntity;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<BookEntity , Long> {
}
