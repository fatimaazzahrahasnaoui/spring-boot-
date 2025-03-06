package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    // Liste simulant la base de données en mémoire
    private List<User> users = new ArrayList<>();

    // Méthode pour créer un utilisateur
    @Transactional
    public User createUser(String name, String email) {
        User user = new User(name, email);
        users.add(user);  // Simule le stockage de l'utilisateur
        return user;
    }

    // Méthode pour récupérer tous les utilisateurs
    public List<User> getAllUsers() {
        return users;
    }

    // Méthode pour obtenir un utilisateur par email
    public Optional<User> getUserByEmail(String email) {
        return users.stream()
                .filter(user -> user.getEmail().equals(email))
                .findFirst();
    }

    // Méthode pour supprimer un utilisateur par email
    @Transactional
    public boolean deleteUser(String email) {
        return users.removeIf(user -> user.getEmail().equals(email));  // Simule la suppression
    }


}
