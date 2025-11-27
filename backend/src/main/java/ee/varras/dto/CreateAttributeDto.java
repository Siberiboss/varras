package ee.varras.dto;

import ee.varras.model.AttributeType;
import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public class CreateAttributeDto {
    private String name;
    private AttributeType type;

    public String getName() { return name; }
    public AttributeType getType() { return type; }

    public void setName(String name) { this.name = name; }
    public void setType(AttributeType type) { this.type = type; }
}
