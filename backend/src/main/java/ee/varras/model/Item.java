package ee.varras.model;

import jakarta.persistence.*;
import java.util.Map;
import ee.varras.util.MapToJsonConverter;
import io.micronaut.serde.annotation.Serdeable;

@Serdeable
@Entity
@Table(name = "item")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;
    private String condition;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @Convert(converter = MapToJsonConverter.class)
    @Column(columnDefinition = "TEXT")
    private Map<String, Object> dynamicFields;

    public Long getId() { return id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getCondition() { return condition; }

    public Category getCategory() { return category; }
    public void setCategory(Category category) { this.category = category; }
    public void setCondition(String condition) { this.condition = condition; }

    public Map<String, Object> getDynamicFields() { return dynamicFields; }
    public void setDynamicFields(Map<String, Object> dynamicFields) { this.dynamicFields = dynamicFields; }

    public Item() {}

    public Item(String name, String condition, Category category) {
        this.name = name;
        this.category = category;
        this.condition = condition;
    }

}
