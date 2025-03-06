package com.example.demo.services.impl;

import com.example.demo.models.dtos.CategoryDto;
import com.example.demo.models.entities.CategoryEntity;
import com.example.demo.models.mappers.impl.CategoryMapper;
import com.example.demo.persistence.CategoryRepository;
import com.example.demo.services.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public CategoryServiceImpl(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        CategoryEntity categoryEntity = categoryMapper.mapToEntity(categoryDto);
        CategoryEntity savedCategory = categoryRepository.save(categoryEntity);
        return categoryMapper.mapToDto(savedCategory);
    }

    // @Override
    // public  CategoryDto getCategoryById(Long id) {
    //     CategoryEntity categoryEntity = categoryRepository.findById(id)
    //             .orElseThrow(() -> new RuntimeException("Category not found"));
    //     return categoryMapper.mapToDto(categoryEntity);
    // }
    @Override
    public Optional<CategoryDto> getCategoryById(Long id) {
        return categoryRepository.findById(id).map(categoryMapper::mapToDto);
    }


    @Override
    public List<CategoryDto> getAllCategories() {
        List<CategoryEntity> categories = (List<CategoryEntity>) categoryRepository.findAll();
        return categories.stream().map(categoryMapper::mapToDto).collect(Collectors.toList());
    }

    @Override
    public CategoryDto updateCategory(Long id, CategoryDto categoryDto) {
        CategoryEntity categoryEntity = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));
        categoryEntity.setLabel(categoryDto.getLabel());
        CategoryEntity updatedCategory = categoryRepository.save(categoryEntity);
        return categoryMapper.mapToDto(updatedCategory);
    }

    @Override
    public Optional<CategoryDto> deleteCategory(Long id) {
        Optional<CategoryDto> result = getCategoryById(id) ;
        categoryRepository.deleteById(id);
        return  result ;
    }
}
