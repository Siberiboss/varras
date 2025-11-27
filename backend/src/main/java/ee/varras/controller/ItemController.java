package ee.varras.controller;

import ee.varras.dto.*;
import ee.varras.model.Item;
import ee.varras.service.ItemService;
import io.micronaut.http.annotation.*;
import io.micronaut.http.HttpStatus;
import jakarta.inject.Inject;

@Controller("/categories/{categoryId}/items")
public class ItemController {

    @Inject
    ItemService itemService;

    @Post("/")
    public ItemDto create(Long categoryId, @Body CreateItemDto request) {
        return itemService.create(categoryId, request);
    }

    @Get("/")
    public Iterable<ItemDto> list(Long categoryId) {
        return itemService.list(categoryId);
    }

    @Get("/{itemId}")
    public ItemDto get(Long categoryId, Long itemId) {
        return itemService.get(categoryId, itemId);
    }

    @Put("/{itemId}")
    public ItemDto update(@Body UpdateItemDto req) {
        return itemService.update(req.getCategoryId(), req.getItemId(), req);
    }

    @Delete("/{itemId}")
    @Status(HttpStatus.NO_CONTENT)
    public void delete(Long categoryId, Long itemId) {
        itemService.delete(categoryId, itemId);
    }
}
