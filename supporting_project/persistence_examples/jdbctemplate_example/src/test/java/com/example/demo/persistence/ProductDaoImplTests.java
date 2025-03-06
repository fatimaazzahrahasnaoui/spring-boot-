package com.example.demo.persistence;

import com.example.demo.models.Product;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.util.AssertionErrors.assertNotNull;

@SpringBootTest
@ExtendWith(SpringExtension.class)

public class ProductDaoImplTests {

    @Autowired
    private  ProductDao productDao ;

    @Test
    public void testSave() {
        // Step 1: Set up the test data
        // Create a new Product object and set its properties.
        // This represents the product that we want to save to the database.
        Product product = new Product();
        product.setId(Long.valueOf(111));  // Unique ID for the product
        product.setLabel("NewProduct");    // Name/label for the product
        product.setPrice(200.0);           // Price for the product

        // Step 2: Call the method under test (the 'save' method)
        // We invoke the save method on the productDao object and pass the product object to be saved.
        // This method is expected to return an integer indicating how many rows were affected.
        // A typical behavior in JDBC is returning the number of rows affected (1 in case of a successful insert).
        int result = productDao.save(product);

        // Step 3: Verify the result using assertions
        // After calling the 'save' method, we assert that the result is 1.
        // This means the product was successfully inserted into the database (one row affected).
        assertEquals( 1, result);
    }



    @Test
    public void testUpdate() {
        // Step 1: Save a new product to the database
        // Create a new Product object with initial values.
        // This will be used to test the update functionality.
        Product product = new Product();
        product.setId(Long.valueOf(101));   // Unique ID for the product
        product.setLabel("OldProduct");     // Initial label for the product
        product.setPrice(150.0);            // Initial price for the product

        // Save the product to the database using the 'save' method
        // The 'save' method should return 1 if the product was successfully saved.
        int saveResult = productDao.save(product);

        // Verify that the product was successfully saved (1 row should be affected)
        assertEquals( 1, saveResult);

        // Step 2: Modify the product and update it in the database
        // Change the product's label and price.
        product.setLabel("NewProduct");   // New label for the product
        product.setPrice(250.0);          // New price for the product

        // Update the product in the database using the 'update' method
        // The 'update' method should return 1 if the product was successfully updated.
        int updateResult = productDao.update(product);

        // Step 3: Verify the result of the update operation
        // The update should return 1 if the update was successful (one row affected).
        assertEquals( 1, updateResult);
    }


    @Test
    public void testDelete() {
        // Step 1: Save a new product to the database
        // Create a new Product object with initial values.
        // This product will be saved to the database and then deleted.
        Product product = new Product();
        product.setId(Long.valueOf(101));  // Set a unique ID for the product
        product.setLabel("OldProduct");    // Set the label for the product
        product.setPrice(150.0);           // Set the price for the product

        // Save the product to the database using the 'save' method.
        // The 'save' method should return 1 if the product is successfully saved.
        int saveResult = productDao.save(product);

        // Verify that the product was successfully saved (1 row should be affected).
        assertEquals( 1, saveResult , "Product should be successfully saved and return 1 as the result");

        // Step 2: Delete the product from the database
        // After saving the product, we proceed to delete it using the 'deleteById' method.
        // This method should return 1 if the product was successfully deleted from the database.
        int deleteResult = productDao.deleteById(product.getId());

        // Step 3: Verify the result of the delete operation
        // The delete operation should return 1, indicating that one row (the product) was deleted.
        assertEquals( 1, deleteResult , "Product should be successfully deleted and return 1 as the result");

        // Additional Consideration:
        // Optionally, you could also verify if the product no longer exists in the database
        // by attempting to retrieve it and asserting that the result is empty (not found).
    }


    @Test
    public void testFindById() {
        // Step 1: Save a new product to the database with a manually assigned ID
        // Create a new Product object and set its properties, including a unique ID.
        Product product = new Product();
        product.setId(Long.valueOf(101));  // Manually assigning the ID
        product.setLabel("OldProduct");    // Set the label of the product
        product.setPrice(150.0);           // Set the price of the product

        // Save the product to the database using the 'save' method
        // The 'save' method should return 1 if the product was successfully inserted.
        int saveResult = productDao.save(product);

        // Assert that the save operation was successful (1 row should be affected)
        assertEquals( 1, saveResult , "Product should be successfully saved and return 1 as the result");

        // Step 2: Retrieve the product by ID using the 'findById' method
        // The 'findById' method should return an Optional containing the product if found.
        Optional<Product> result = productDao.findById(product.getId());

        // Step 3: Assertions
        // We first assert that the product was found (Optional is present).
        assertTrue(result.isPresent(), "Product should be retrievable by its ID");

        // Then we assert that the retrieved product's ID matches the saved product's ID.
        assertEquals( product.getId(), result.get().getId() , "Product IDs should match");

        // Next, we assert that the label of the saved product matches the retrieved product.
        assertEquals("Product labels should match", product.getLabel(), result.get().getLabel());

        // Finally, we assert that the price of the saved product matches the retrieved product.
        assertEquals(product.getPrice(), result.get().getPrice(), 0.01 , "Product prices should match"); // Adding a delta for floating-point comparison
    }



    @Test
    public void testFindAll() {
        // Step 1: Save a few products to the database
        // Create and save the first product.
        Product product1 = new Product();
        product1.setId(Long.valueOf(101));
        product1.setLabel("Product1");
        product1.setPrice(100.0);
        productDao.save(product1);  // Save the first product

        // Create and save the second product.
        Product product2 = new Product();
        product2.setId(Long.valueOf(102));
        product2.setLabel("Product2");
        product2.setPrice(200.0);
        productDao.save(product2);  // Save the second product

        // Create and save the third product.
        Product product3 = new Product();
        product3.setId(Long.valueOf(103));
        product3.setLabel("Product3");
        product3.setPrice(300.0);
        productDao.save(product3);  // Save the third product

        // Step 2: Retrieve all products using the 'findAll' method
        // The 'findAll' method should return a list of products saved earlier.
        List<Product> products = productDao.findAll();

        // Step 3: Assertions
        // Assert that the list of products is not null and contains at least 3 products.
        assertNotNull("The list of products should not be null", products);
        assertEquals( 3, products.size() , "The list of products should contain 3 products");

        // Verify that the products in the list match the expected products by checking labels
        assertTrue(products.stream().anyMatch(p -> p.getLabel().equals("Product1")) );
        assertTrue(products.stream().anyMatch(p -> p.getLabel().equals("Product2")));
        assertTrue(products.stream().anyMatch(p -> p.getLabel().equals("Product3")));
    }




}
