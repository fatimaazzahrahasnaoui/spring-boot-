package com.example.demo.controllers;

import com.example.demo.models.dtos.CategoryDto;
import com.example.demo.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")


public class CategoryController {

    private final CategoryService categoryService;
    @Autowired
    public CategoryController(CategoryService categoryService) {this.categoryService = categoryService;};

    @PostMapping(path = "/save")
    public ResponseEntity<CategoryDto>createCategory(@RequestBody CategoryDto categoryDto) {
        CategoryDto savedCategory = categoryService.createCategory(categoryDto);
        return new ResponseEntity<CategoryDto>(savedCategory, HttpStatus.CREATED);

    }

    @GetMapping(path = "/one/{id}")
    public ResponseEntity<CategoryDto> getCategory(@PathVariable Long id) {
        return categoryService.getCategoryById(id)
                .map(CategoryDto -> new ResponseEntity<CategoryDto>(CategoryDto, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/all")
    public ResponseEntity<List<CategoryDto>>getAll(){
        List<CategoryDto>listcategories = categoryService.getAllCategories();
        return new ResponseEntity<List<CategoryDto>>(listcategories,HttpStatus.OK);
    }

    @PutMapping(path = "/update/{id}")
    public ResponseEntity<CategoryDto>update(@PathVariable Long id, @RequestBody CategoryDto categoryDto) {

        CategoryDto savedCategory = categoryService.updateCategory(id, categoryDto);
        return  new ResponseEntity<CategoryDto>(savedCategory, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<CategoryDto> delete(@PathVariable Long id) {
        return categoryService.deleteCategory(id)
                .map(CategoryDto -> new ResponseEntity<CategoryDto>(CategoryDto, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

}