package com.example.demo.persistence;

import com.example.demo.models.Product;

import java.util.List;
import java.util.Optional;

public interface ProductDao {
    int save(Product product);
    int update(Product product);
    int deleteById(Long id);
    Optional<Product> findById(Long id);
    List<Product> findAll();
}
