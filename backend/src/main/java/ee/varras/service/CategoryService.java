package ee.varras.service;

import ee.varras.dto.*;
import ee.varras.model.Category;
import ee.varras.repository.CategoryRepository;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import java.util.List;
import java.util.stream.Collectors;

@Singleton
public class CategoryService {

    @Inject
    CategoryRepository categoryRepository;

    public CategoryDto create(CreateCategoryDto dto) {
        Category c = new Category();
        c.setName(dto.getName());
        c.setUsername(dto.getUsername());
        c = categoryRepository.save(c);
        return toDto(c);
    }

    public CategoryDto get(Long id) {
        Category c = categoryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Category not found: " + id));
        return toDto(c);
    }

    public List<CategoryDto> findAll() {
        return categoryRepository.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
/*
    public CategoryDto update(Long id, UpdateCategoryDto dto) {
        Category c = categoryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Category not found: " + id));

        c.setName(dto.getName());
        c = categoryRepository.update(c);

        return toDto(c);
    }*/

    public void deleteById(Long id) {
        if (!categoryRepository.existsById(id)) {
            throw new IllegalArgumentException("Category not found: " + id);
        }
        categoryRepository.deleteById(id);
    }

    private CategoryDto toDto(Category c) {
        return new CategoryDto(c.getId(), c.getName());
    }
}
