package com.example.demo.services;

import com.example.demo.models.dtos.CategoryDto;
import com.example.demo.persistence.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class CategoryServicesIntegrationTests {

    private final CategoryRepository categoryRepository;
    private final CategoryService categoryService;

    @Autowired
    public CategoryServicesIntegrationTests(CategoryRepository categoryRepository, CategoryService categoryService) {
        this.categoryRepository = categoryRepository;
        this.categoryService = categoryService;
    }

    @BeforeEach
    public void setUp() {
        this.categoryRepository.deleteAll();
    }

    @Test
    public void testSaveCategory() {
        CategoryDto category = CategoryDto.builder()
                .label("Technology")
                .build();

        CategoryDto savedCategory = categoryService.createCategory(category);

        assertThat(savedCategory).isNotNull();
        assertThat(savedCategory.getId()).isNotNull();
        assertThat(savedCategory.getLabel()).isEqualTo(category.getLabel());
    }

    @Test
    public void testFindCategoryById() {
        CategoryDto category = CategoryDto.builder()
                .label("Technology")
                .build();

        CategoryDto savedCategory = categoryService.createCategory(category);
        Optional<CategoryDto> result = categoryService.getCategoryById(savedCategory.getId());

        assertThat(result).isPresent();
        assertThat(result.get().getLabel()).isEqualTo(category.getLabel());
    }

    @Test
    public void testFindAllCategories() {
        CategoryDto category1 = CategoryDto.builder()
                .label("Technology")
                .build();

        CategoryDto category2 = CategoryDto.builder()
                .label("Health")
                .build();

        categoryService.createCategory(category1);
        categoryService.createCategory(category2);

        List<CategoryDto> result = categoryService.getAllCategories();

        assertThat(result).hasSize(2);
        assertThat(result.stream().map(CategoryDto::getLabel)).contains("Technology", "Health");
    }

    @Test
    public void testUpdateCategory() {
        CategoryDto category = CategoryDto.builder()
                .label("Technology")
                .build();

        CategoryDto savedCategory = categoryService.createCategory(category);
        savedCategory.setLabel("Tech and Innovation");

        CategoryDto updatedCategory = categoryService.updateCategory(savedCategory.getId(), savedCategory);

        assertThat(updatedCategory.getLabel()).isEqualTo("Tech and Innovation");
    }

    @Test
    public void testDeleteCategory() {
        CategoryDto category = CategoryDto.builder()
                .label("Technology")
                .build();

        CategoryDto savedCategory = categoryService.createCategory(category);
        categoryService.deleteCategory(savedCategory.getId());

        Optional<CategoryDto> result = categoryService.getCategoryById(savedCategory.getId());

        assertThat(result).isNotPresent();
    }
}
