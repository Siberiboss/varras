package ee.varras.dto;

import ee.varras.model.AttributeType;
import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public class AttributeDto {
    private Long id;
    private Long categoryId;
    private String name;
    private AttributeType type; // STRING, NUMBER, DATE, BOOLEAN

    public AttributeDto() {}

    public AttributeDto(Long id, Long categoryId, String name, AttributeType type) {
        this.id = id;
        this.categoryId = categoryId;
        this.name = name;
        this.type = type;
    }

    public Long getId() { return id; }
    public Long getCategoryId() { return categoryId; }
    public String getName() { return name; }
    public AttributeType getType() { return type; }

    public void setId(Long id) { this.id = id; }
    public void setCategoryId(Long categoryId) { this.categoryId = categoryId; }
    public void setName(String name) { this.name = name; }
    public void setType(AttributeType type) { this.type = type; }
}
