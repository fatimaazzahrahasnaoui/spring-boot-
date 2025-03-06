package com.example.demo.persistence;

import com.example.demo.models.entities.CategoryEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest  // Configures an in-memory database for testing and sets up JPA repositories
public class CategoryRepositoryUnitTests {

    @Autowired
    private CategoryRepository categoryRepository;

    // Test Create operation (Save Category)
    @Test
    public void testSaveCategory() {
        // Create a CategoryEntity instance using the builder pattern
        CategoryEntity category = CategoryEntity.builder()
                .label("Science Fiction")
                .build();

        // Save the category to the repository
        CategoryEntity result = categoryRepository.save(category);

        // Assertions
        assertThat(result).isNotNull();
        assertThat(result.getLabel()).isEqualTo("Science Fiction");
    }

    // Test Read operation (Find Category by ID)
    @Test
    public void testFindCategoryById() {
        // Create and save a category
        CategoryEntity category = CategoryEntity.builder()
                .label("Science Fiction")
                .build();
        CategoryEntity savedCategory = categoryRepository.save(category);

        // Retrieve the category by ID
        CategoryEntity foundCategory = categoryRepository.findById(savedCategory.getId()).orElse(null);

        // Assertions
        assertThat(foundCategory).isNotNull();
        assertThat(foundCategory.getId()).isEqualTo(savedCategory.getId());
        assertThat(foundCategory.getLabel()).isEqualTo("Science Fiction");
    }

    // Test Update operation (Update Category)
    @Test
    public void testUpdateCategory() {
        // Create and save a category
        CategoryEntity category = CategoryEntity.builder()
                .label("Science Fiction")
                .build();
        CategoryEntity savedCategory = categoryRepository.save(category);

        // Update the category label
        savedCategory.setLabel("Fantasy");

        // Save the updated category
        CategoryEntity updatedCategory = categoryRepository.save(savedCategory);

        // Assertions
        assertThat(updatedCategory).isNotNull();
        assertThat(updatedCategory.getLabel()).isEqualTo("Fantasy");
    }

    // Test Delete operation (Delete Category)
    @Test
    public void testDeleteCategory() {
        // Create and save a category
        CategoryEntity category = CategoryEntity.builder()
                .label("Science Fiction")
                .build();
        CategoryEntity savedCategory = categoryRepository.save(category);

        // Delete the category
        categoryRepository.delete(savedCategory);

        // Try to retrieve the deleted category by ID
        CategoryEntity deletedCategory = categoryRepository.findById(savedCategory.getId()).orElse(null);

        // Assertions
        assertThat(deletedCategory).isNull();
    }

    // Test Find All operation (Find All Categories)
    @Test
    public void testFindAllCategories() {
        // Create and save two categories
        CategoryEntity category1 = CategoryEntity.builder()
                .label("Science Fiction")
                .build();
        categoryRepository.save(category1);

        CategoryEntity category2 = CategoryEntity.builder()
                .label("Fantasy")
                .build();
        categoryRepository.save(category2);

        // Retrieve all categories
        Iterable<CategoryEntity> categories = categoryRepository.findAll();

        // Assertions
        assertThat(categories).hasSize(2);
    }
}
