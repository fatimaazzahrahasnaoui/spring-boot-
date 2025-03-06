package com.example.demo.services;

import com.example.demo.models.dtos.CategoryDto;
import java.util.List;
import java.util.Optional;

public interface CategoryService {
    CategoryDto createCategory(CategoryDto categoryDto);
    Optional<CategoryDto> getCategoryById(Long id);
    List<CategoryDto> getAllCategories();
    CategoryDto updateCategory(Long id, CategoryDto categoryDto);
    Optional<CategoryDto> deleteCategory(Long id);
}
