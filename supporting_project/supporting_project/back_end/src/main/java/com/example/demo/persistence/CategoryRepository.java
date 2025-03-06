package com.example.demo.persistence;
import com.example.demo.models.entities.CategoryEntity;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<CategoryEntity , Long> {
}
