package ee.varras.dto;

import io.micronaut.serde.annotation.Serdeable;
import java.util.Map;

@Serdeable
public class CreateItemDto {
    private String name;
    private String condition;
    private Map<Long, String> attributes;

    public String getName() { return name; }
    public String getCondition() { return condition; }
    public Map<Long, String> getAttributes() { return attributes; }

    public void setName(String name) { this.name = name; }
    public void setCondition(String condition) { this.condition = condition; }
    public void setAttributes(Map<Long, String> attributes) { this.attributes = attributes; }
}
