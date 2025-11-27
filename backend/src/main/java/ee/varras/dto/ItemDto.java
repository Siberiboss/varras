package ee.varras.dto;

import io.micronaut.serde.annotation.Serdeable;
import java.util.Map;

@Serdeable
public class ItemDto {
    private Long id;
    private String name;
    private Long categoryId;
    private Map<Long, String> attributes;

    public ItemDto() {}

    public ItemDto(String name, Long categoryId, Map<Long, String> attributes) {
        this.categoryId = categoryId;
        this.attributes = attributes;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public Long getCategoryId() { return categoryId; }
    public Map<Long, String> getAttributes() { return attributes; }

    public void setId(Long id) { this.id = id; }
    public void setName() { this.name = name; }
    public void setCategoryId(Long categoryId) { this.categoryId = categoryId; }
    public void setAttributes(Map<Long, String> attributes) { this.attributes = attributes; }
}
