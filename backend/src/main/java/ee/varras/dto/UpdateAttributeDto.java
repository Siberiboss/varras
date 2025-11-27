package ee.varras.dto;

import ee.varras.model.AttributeType;
import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public class UpdateAttributeDto {
    private Long id;
    private Long categoryId;
    private String name;
    private AttributeType type;

    public String getName() { return name; }
    public AttributeType getType() { return type; }
    public Long getCategoryId() { return categoryId; }
    public AttributeType getAttributeType() {
        return type;
    }

    public void setName(String name) { this.name = name; }
    public void setType(AttributeType type) { this.type = type; }
}
