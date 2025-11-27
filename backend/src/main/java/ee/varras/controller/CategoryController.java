package ee.varras.controller;

import ee.varras.dto.CategoryDto;
import ee.varras.dto.CreateCategoryDto;
import ee.varras.service.CategoryService;
import io.micronaut.http.annotation.*;

import jakarta.inject.Inject;
import java.util.List;

@Controller("/categories")
public class CategoryController {

    @Inject
    CategoryService service;

    @Get
    public List<CategoryDto> list() {
         return service.findAll();
    }

    @Post
    public CategoryDto create(@Body CreateCategoryDto req) {
        return service.create(req);
    }

    /*
    @Put("/{id}")
    public Category update(Long id, @Body Category c) {
        c.setId(id);
        return service.update(c);
    }*/

    @Delete("/{id}")
    public void delete(Long id) {
        service.deleteById(id);
    }
}
