package ee.varras.controller;

import ee.varras.dto.*;
import ee.varras.service.AttributeService;
import io.micronaut.http.annotation.*;
import io.micronaut.http.HttpStatus;
import jakarta.inject.Inject;

@Controller("/categories/{categoryId}/attributes")
public class AttributeController {

    @Inject
    AttributeService attributeService;

    @Post("/")
    public AttributeDto create(Long categoryId, @Body CreateAttributeDto req) {
        return attributeService.create(categoryId, req);
    }

    @Get("/")
    public Iterable<AttributeDto> list(Long categoryId) {
        return attributeService.findAll(categoryId);
    }

    @Put("/{attributeId}")
    public AttributeDto update(Long categoryId, Long attributeId, @Body UpdateAttributeDto req
    ) {
        return attributeService.update(categoryId, attributeId, req);
    }

    @Delete("/{attributeId}")
    @Status(HttpStatus.NO_CONTENT)
    public void delete(Long categoryId, Long attributeId) {
        attributeService.delete(categoryId, attributeId);
    }
}
