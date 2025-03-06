package com.example.demo.persistence;

import com.example.demo.models.entities.ProductEntity;
import com.example.demo.repositories.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class ProductRepositoryTests {
    @Autowired
    private ProductRepository productRepository;

    @Test
    public void testCreate() {
        // Existing create test code here...
    }

    @Test
    public void testFindAll() {
        // Create and save a product
        ProductEntity productEntity = new ProductEntity();
        productEntity.setLabel("MacBook Air");
        productEntity.setPrice(950.00);
        productEntity.setCreatedBy("User");
        productRepository.save(productEntity);

        // Retrieve all products
        List<ProductEntity> products = (List<ProductEntity>) productRepository.findAll();

        // Assert: list should contain at least one product
        assertTrue(products.size() > 0, "Product list should not be empty after saving a product");
    }

    @Test
    public void testUpdate() {
        // Create and save a product
        ProductEntity productEntity = new ProductEntity();
        productEntity.setLabel("iPhone");
        productEntity.setPrice(750.00);
        productEntity.setCreatedBy("User");
        ProductEntity savedProduct = productRepository.save(productEntity);

        // Update the product
        savedProduct.setLabel("iPhone Pro");
        savedProduct.setPrice(999.99);
        ProductEntity updatedProduct = productRepository.save(savedProduct);

        // Retrieve updated product
        Optional<ProductEntity> retrievedProduct = productRepository.findById(updatedProduct.getId());
        assertTrue(retrievedProduct.isPresent(), "Updated product should be found in the repository");

        // Verify the updated fields
        assertEquals("iPhone Pro", retrievedProduct.get().getLabel(), "Product label should be updated");
        assertEquals(999.99, retrievedProduct.get().getPrice(), "Product price should be updated");
    }

    @Test
    public void testDeleteById() {
        // Create and save a product
        ProductEntity productEntity = new ProductEntity();
        productEntity.setLabel("Headphones");
        productEntity.setPrice(150.00);
        productEntity.setCreatedBy("User");
        ProductEntity savedProduct = productRepository.save(productEntity);

        // Delete the product by ID
        productRepository.deleteById(savedProduct.getId());

        // Verify the product no longer exists
        Optional<ProductEntity> deletedProduct = productRepository.findById(savedProduct.getId());
        assertFalse(deletedProduct.isPresent(), "Product should not be found after deletion");
    }

    @Test
    public void testDeleteAll() {
        // Create and save multiple products
        ProductEntity product1 = new ProductEntity();
        product1.setLabel("Keyboard");
        product1.setPrice(100.00);
        product1.setCreatedBy("User");

        ProductEntity product2 = new ProductEntity();
        product2.setLabel("Mouse");
        product2.setPrice(50.00);
        product2.setCreatedBy("User");

        productRepository.save(product1);
        productRepository.save(product2);

        // Delete all products
        productRepository.deleteAll();

        // Verify the repository is empty
        List<ProductEntity> products = (List<ProductEntity>) productRepository.findAll();
        assertEquals(0, products.size(), "Repository should be empty after deleteAll operation");
    }
}
