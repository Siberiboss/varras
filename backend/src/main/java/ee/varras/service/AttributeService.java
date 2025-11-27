package ee.varras.service;

import ee.varras.dto.*;
import ee.varras.model.Attribute;
import ee.varras.model.Category;
import ee.varras.repository.AttributeRepository;
import ee.varras.repository.CategoryRepository;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Singleton
public class AttributeService {

    @Inject
    AttributeRepository attributeRepository;

    @Inject
    CategoryRepository categoryRepository;

    public AttributeDto create(Long categoryId, CreateAttributeDto dto) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new IllegalArgumentException("Category not found: " + categoryId));

        Attribute a = new Attribute();
        a.setName(dto.getName());
        a.setType(dto.getType());
        a.setCategory(category);

        a = attributeRepository.save(a);

        return toDto(a);
    }

    public Iterable<AttributeDto> findAll(Long categoryId) {
        return attributeRepository.findAllByCategoryId(categoryId).stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public AttributeDto update(Long categoryId, Long attributeId, UpdateAttributeDto dto) {

        Attribute a = attributeRepository.findById(attributeId)
                .orElseThrow(() -> new IllegalArgumentException("Attribute not found: " + attributeId));

        if (!a.getCategory().getId().equals(categoryId)) {
            throw new IllegalArgumentException("Attribute does not belong to category");
        }

        a.setName(dto.getName());
        a.setType(dto.getType());

        a = attributeRepository.update(a);
        return toDto(a);
    }

    public void delete(Long categoryId, Long attributeId) {
        Attribute a = attributeRepository.findById(attributeId)
                .orElseThrow(() -> new IllegalArgumentException("Attribute not found: " + attributeId));

        if (!a.getCategory().getId().equals(categoryId)) {
            throw new IllegalStateException("Attribute does not belong to this category");
        }

        attributeRepository.deleteById(attributeId);
    }

    private AttributeDto toDto(Attribute a) {
        return new AttributeDto(
                a.getId(),
                a.getCategory().getId(),
                a.getName(),
                a.getType()
        );
    }
}
