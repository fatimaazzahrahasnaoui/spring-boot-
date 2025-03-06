package com.example.demo.persistence;
import com.example.demo.models.entities.AuthorEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest  // This annotation configures an in-memory database and sets up the context for JPA tests
public class AuthorRepositoryUnitTests {

    @Autowired
    private AuthorRepository authorRepository;

    // Test Create operation (Save)
    @Test
    public void testSaveAuthor() {
        // Create an AuthorEntity instance using the builder pattern
        AuthorEntity author = AuthorEntity.builder()
                .name("Bilal")
                .age(45)
                .hidedenField("hidden")
                .build();

        // Save the author to the repository
        AuthorEntity result = authorRepository.save(author);

        // Assertions
        assertThat(result).isNotNull();
        assertThat(result.getName()).isEqualTo("Bilal");
        assertThat(result.getAge()).isEqualTo(45);
        assertThat(result.getHidedenField()).isEqualTo("hidden");
    }

    // Test Read operation (Find by ID)
    @Test
    public void testFindAuthorById() {
        // Create and save an author
        AuthorEntity author = AuthorEntity.builder()
                .name("Bilal")
                .age(45)
                .hidedenField("hidden")
                .build();
        AuthorEntity savedAuthor = authorRepository.save(author);

        // Retrieve the author by ID
        Optional<AuthorEntity> foundAuthor = authorRepository.findById(savedAuthor.getId());

        // Assertions
        assertThat(foundAuthor).isPresent();
        assertThat(foundAuthor.get().getId()).isEqualTo(savedAuthor.getId());
        assertThat(foundAuthor.get().getName()).isEqualTo("Bilal");
        assertThat(foundAuthor.get().getAge()).isEqualTo(45);
    }

    // Test Update operation
    @Test
    public void testUpdateAuthor() {
        // Create and save an author
        AuthorEntity author = AuthorEntity.builder()
                .name("Bilal")
                .age(45)
                .hidedenField("hidden")
                .build();
        AuthorEntity savedAuthor = authorRepository.save(author);

        // Update the author's age
        savedAuthor.setAge(50);

        // Save the updated author
        AuthorEntity updatedAuthor = authorRepository.save(savedAuthor);

        // Assertions
        assertThat(updatedAuthor).isNotNull();
        assertThat(updatedAuthor.getAge()).isEqualTo(50);
    }

    // Test Delete operation
    @Test
    public void testDeleteAuthor() {
        // Create and save an author
        AuthorEntity author = AuthorEntity.builder()
                .name("Bilal")
                .age(45)
                .hidedenField("hidden")
                .build();
        AuthorEntity savedAuthor = authorRepository.save(author);

        // Delete the author
        authorRepository.delete(savedAuthor);

        // Try to retrieve the deleted author by ID
        Optional<AuthorEntity> deletedAuthor = authorRepository.findById(savedAuthor.getId()) ;

        // Assertions
        assertThat(deletedAuthor).isNotPresent();
    }

    // Test Find All operation
    @Test
    public void testFindAllAuthors() {
        // Create and save authors
        AuthorEntity author1 = AuthorEntity.builder()
                .name("Bilal")
                .age(45)
                .hidedenField("hidden")
                .build();
        authorRepository.save(author1);

        AuthorEntity author2 = AuthorEntity.builder()
                .name("Ali")
                .age(38)
                .hidedenField("hidden")
                .build();
        authorRepository.save(author2);

        // Retrieve all authors
        Iterable<AuthorEntity> authors = authorRepository.findAll();

        // Assertions
        assertThat(authors).hasSize(2);
    }
}
