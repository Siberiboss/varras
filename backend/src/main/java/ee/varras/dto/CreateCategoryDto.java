package ee.varras.dto;

import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public class CreateCategoryDto {
    private String name;
    private String username;

    public CreateCategoryDto(String name) {
        this.name = name;
        this.username = "testija";
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
}
