package com.example.demo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceTest {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceTest.class);

    @Autowired
    private UserService userService; // Injection du service

    @BeforeEach
    void setUp() {
        // Simulation de données avant chaque test
        logger.info("Création d'utilisateurs de test...");
        userService.createUser("John Doe", "john@example.com");
        userService.createUser("Jane Doe", "jane@example.com");
    }

    @Test
    void testCreateUser() {
        logger.info("Test de la création d'un utilisateur...");
        User user = userService.createUser("Mark Smith", "mark@example.com");
        assertNotNull(user);
        assertEquals("Mark Smith", user.getName());
        logger.info("Utilisateur créé: {}", user.getName());
    }

    @Test
    void testGetUserByEmail() {
        logger.info("Test de récupération d'un utilisateur par email...");
        User user = userService.getUserByEmail("john@example.com").orElse(null);
        assertNotNull(user);
        assertEquals("John Doe", user.getName());
        logger.info("Utilisateur trouvé: {}", user.getName());
    }

    @Test
    void testGetAllUsers() {
        logger.info("Test de récupération de tous les utilisateurs...");
        List<User> users = userService.getAllUsers();
        assertEquals(2, users.size());  // Deux utilisateurs sont créés dans setUp()
        logger.info("Nombre d'utilisateurs récupérés: {}", users.size());
    }

    @Test
    void testDeleteUser() {
        logger.info("Test de suppression d'un utilisateur...");
        boolean deleted = userService.deleteUser("john@example.com");
        assertTrue(deleted);
        assertTrue(userService.getUserByEmail("john@example.com").isEmpty());
        logger.info("Utilisateur supprimé avec succès.");
    }
}
