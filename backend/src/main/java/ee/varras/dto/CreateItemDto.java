package ee.varras.dto;

import io.micronaut.serde.annotation.Serdeable;
import java.util.Map;

@Serdeable
public class CreateItemDto {
    private String name;
    private Map<Long, String> attributes;

    public String getName() { return name; }
    public Map<Long, String> getAttributes() { return attributes; }

    public void setName(String name) { this.name = name; }
    public void setAttributes(Map<Long, String> attributes) { this.attributes = attributes; }
}
