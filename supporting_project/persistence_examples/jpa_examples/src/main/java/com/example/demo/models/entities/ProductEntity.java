package com.example.demo.models.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "products")
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "label", nullable = false)
    private String label;

    @Column(name = "price", nullable = false)
    private Double price;

    // Internal field to track who created the product, not intended for exposure through a DTO
    @Column(name = "created_by", nullable = false)
    private String createdBy;
}
