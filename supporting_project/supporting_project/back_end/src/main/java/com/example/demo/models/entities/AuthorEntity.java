package com.example.demo.models.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "authors")

public class AuthorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id ;

    @Column(name = "name" , nullable = false )
    private  String name ;

    @Column(name = "age" , nullable = false)
    private  int age ;

    // A piece of information which will stay encapsulated in the entity :
  //    @Column(name = "hiddenField" , nullable = true)
    private String hidedenField ;

}
