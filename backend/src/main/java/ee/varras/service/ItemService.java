package ee.varras.service;

import ee.varras.dto.*;
import ee.varras.model.*;
import ee.varras.repository.*;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import java.util.*;

@Singleton
public class ItemService {

    @Inject
    ItemRepository itemRepository;

    @Inject
    CategoryRepository categoryRepository;

    @Inject
    AttributeRepository attributeRepository;

    @Inject
    ItemAttributeValueRepository itemAttributeValueRepository;

    public ItemDto create(Long categoryId, CreateItemDto dto) {

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new IllegalArgumentException("Category not found: " + categoryId));

        Item item = new Item(dto.getName(), category);
        item = itemRepository.save(item);
/*
        // save attribute values
        for (var entry : dto.getValues().entrySet()) {
            Long attributeId = Long.valueOf(entry.getKey());

            Attribute attr = attributeRepository.findById(attributeId)
                    .orElseThrow(() -> new IllegalArgumentException("Attribute not found: " + attributeId));

            if (!attr.getCategory().getId().equals(categoryId)) {
                throw new IllegalArgumentException("Attribute does not belong to category");
            }

            ItemAttributeValue val = new ItemAttributeValue();
            val.setItem(item);
            val.setAttribute(attr);
            val.setValue(entry.getValue());

            itemAttributeValueRepository.save(val);
        }*/

        return toDto(item);
    }

    public ItemDto get(Long categoryId, Long itemId) {
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new IllegalArgumentException("Item not found: " + itemId));

        if (!item.getCategory().getId().equals(categoryId)) {
            throw new IllegalArgumentException("Item does not belong to category");
        }

        return toDto(item);
    }

    public Iterable<ItemDto> list(Long categoryId) {
        Iterable<Item> items = itemRepository.findAllByCategoryId(categoryId);
        List<ItemDto> result = new ArrayList<>();
        for (Item i : items) {
            result.add(toDto(i));
        }
        return result;
    }

    public ItemDto update(Long categoryId, Long itemId, UpdateItemDto dto) {

        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new IllegalArgumentException("Item not found: " + itemId));

        if (!item.getCategory().getId().equals(categoryId)) {
            throw new IllegalArgumentException("Item does not belong to category");
        }
/*
        item.setName(dto.getName());
        item = itemRepository.update(item);

        // replace attribute values
        itemAttributeValueRepository.deleteAllByItemId(itemId);

        for (var entry : dto.getValues().entrySet()) {
            Long attributeId = Long.valueOf(entry.getKey());

            Attribute attr = attributeRepository.findById(attributeId)
                    .orElseThrow(() -> new IllegalArgumentException("Attribute not found: " + attributeId));

            ItemAttributeValue val = new ItemAttributeValue();
            val.setItem(item);
            val.setAttribute(attr);
            val.setValue(entry.getValue());

            itemAttributeValueRepository.save(val);
        }
*/
        return toDto(item);
    }

    public void delete(Long categoryId, Long itemId) {
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new IllegalArgumentException("Item not found: " + itemId));

        if (!item.getCategory().getId().equals(categoryId)) {
            throw new IllegalArgumentException("Item does not belong to category");
        }

        itemAttributeValueRepository.deleteAllByItemId(itemId);
        itemRepository.deleteById(itemId);
    }

    private ItemDto toDto(Item i) {
        Map<Long, String> values = new HashMap<>();

        for (ItemAttributeValue v : itemAttributeValueRepository.findAllByItemId(i.getId())) {
            values.put(v.getAttribute().getId(), v.getValue());
        }

        return new ItemDto(
                i.getId(),
                i.getCategory().getId(),
                values
        );
    }
}
