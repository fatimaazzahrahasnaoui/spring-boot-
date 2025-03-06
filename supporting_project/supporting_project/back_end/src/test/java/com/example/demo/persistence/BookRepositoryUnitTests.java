package com.example.demo.persistence;

import com.example.demo.models.entities.BookEntity;
import com.example.demo.models.entities.AuthorEntity;
import com.example.demo.models.entities.CategoryEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest  // Configures an in-memory database for testing and sets up JPA repositories
public class BookRepositoryUnitTests {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    // Test Create operation (Save Book)
    @Test
    public void testSaveBook() {
        // Create a CategoryEntity instance
        CategoryEntity category = CategoryEntity.builder()
                .label("Science Fiction")
                .build();
        categoryRepository.save(category);

        // Create an AuthorEntity instance
        AuthorEntity author = AuthorEntity.builder()
                .name("J.K. Rowling")
                .age(55)
                .hidedenField("hidden")  // assuming this field is in the AuthorEntity
                .build();
        authorRepository.save(author);

        // Create a BookEntity instance using the builder pattern
        BookEntity book = BookEntity.builder()
                .title("Harry Potter and the Philosopher's Stone")
                .publishDate(new Date())
                .author(author)
                .category(category)
                .build();

        // Save the book to the repository
        BookEntity result = bookRepository.save(book);

        // Assertions
        assertThat(result).isNotNull();
        assertThat(result.getTitle()).isEqualTo("Harry Potter and the Philosopher's Stone");
        assertThat(result.getAuthor()).isEqualTo(author);
        assertThat(result.getCategory()).isEqualTo(category);
    }

    // Test Read operation (Find Book by ID)
    @Test
    public void testFindBookById() {
        // Create and save an author and category
        AuthorEntity author = AuthorEntity.builder()
                .name("J.K. Rowling")
                .age(55)
                .build();
        authorRepository.save(author);

        CategoryEntity category = CategoryEntity.builder()
                .label("Fantasy")
                .build();
        categoryRepository.save(category);

        // Create and save a book
        BookEntity book = BookEntity.builder()
                .title("Harry Potter and the Chamber of Secrets")
                .publishDate(new Date())
                .author(author)
                .category(category)
                .build();
        BookEntity savedBook = bookRepository.save(book);

        // Retrieve the book by ID
        BookEntity foundBook = bookRepository.findById(savedBook.getId()).orElse(null);

        // Assertions
        assertThat(foundBook).isNotNull();
        assertThat(foundBook.getId()).isEqualTo(savedBook.getId());
        assertThat(foundBook.getTitle()).isEqualTo("Harry Potter and the Chamber of Secrets");
        assertThat(foundBook.getAuthor()).isEqualTo(author);
        assertThat(foundBook.getCategory()).isEqualTo(category);
    }

    // Test Update operation (Update Book)
    @Test
    public void testUpdateBook() {
        // Create and save an author and category
        AuthorEntity author = AuthorEntity.builder()
                .name("J.K. Rowling")
                .age(55)
                .build();
        authorRepository.save(author);

        CategoryEntity category = CategoryEntity.builder()
                .label("Fantasy")
                .build();
        categoryRepository.save(category);

        // Create and save a book
        BookEntity book = BookEntity.builder()
                .title("Harry Potter and the Philosopher's Stone")
                .publishDate(new Date())
                .author(author)
                .category(category)
                .build();
        BookEntity savedBook = bookRepository.save(book);

        // Update the book's title
        savedBook.setTitle("Harry Potter and the Goblet of Fire");

        // Save the updated book
        BookEntity updatedBook = bookRepository.save(savedBook);

        // Assertions
        assertThat(updatedBook).isNotNull();
        assertThat(updatedBook.getTitle()).isEqualTo("Harry Potter and the Goblet of Fire");
    }

    // Test Delete operation (Delete Book)
    @Test
    public void testDeleteBook() {
        // Create and save an author and category
        AuthorEntity author = AuthorEntity.builder()
                .name("J.K. Rowling")
                .age(55)
                .build();
        authorRepository.save(author);

        CategoryEntity category = CategoryEntity.builder()
                .label("Fantasy")
                .build();
        categoryRepository.save(category);

        // Create and save a book
        BookEntity book = BookEntity.builder()
                .title("Harry Potter and the Philosopher's Stone")
                .publishDate(new Date())
                .author(author)
                .category(category)
                .build();
        BookEntity savedBook = bookRepository.save(book);

        // Delete the book
        bookRepository.delete(savedBook);

        // Try to retrieve the deleted book by ID
        BookEntity deletedBook = bookRepository.findById(savedBook.getId()).orElse(null);

        // Assertions
        assertThat(deletedBook).isNull();
    }

    // Test Find All operation (Find All Books)
    @Test
    public void testFindAllBooks() {
        // Create and save two books
        AuthorEntity author = AuthorEntity.builder()
                .name("J.K. Rowling")
                .age(55)
                .build();
        authorRepository.save(author);

        CategoryEntity category = CategoryEntity.builder()
                .label("Fantasy")
                .build();
        categoryRepository.save(category);

        BookEntity book1 = BookEntity.builder()
                .title("Harry Potter and the Philosopher's Stone")
                .publishDate(new Date())
                .author(author)
                .category(category)
                .build();
        bookRepository.save(book1);

        BookEntity book2 = BookEntity.builder()
                .title("Harry Potter and the Chamber of Secrets")
                .publishDate(new Date())
                .author(author)
                .category(category)
                .build();
        bookRepository.save(book2);

        // Retrieve all books
        Iterable<BookEntity> books = bookRepository.findAll();

        // Assertions
        assertThat(books).hasSize(2);
    }
}
