package ee.varras.dto;

import io.micronaut.serde.annotation.Serdeable;
import java.util.Map;

@Serdeable
public class ItemDto {
    private Long id;
    private Long categoryId;
    private Map<Long, String> attributes;
    // key = attributeId, value = user's input string

    public ItemDto() {}

    public ItemDto(Long id, Long categoryId, Map<Long, String> attributes) {
        this.id = id;
        this.categoryId = categoryId;
        this.attributes = attributes;
    }

    public Long getId() { return id; }
    public Long getCategoryId() { return categoryId; }
    public Map<Long, String> getAttributes() { return attributes; }

    public void setId(Long id) { this.id = id; }
    public void setCategoryId(Long categoryId) { this.categoryId = categoryId; }
    public void setAttributes(Map<Long, String> attributes) { this.attributes = attributes; }
}
