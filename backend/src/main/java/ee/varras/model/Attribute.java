package ee.varras.model;

import io.micronaut.serde.annotation.Serdeable;
import jakarta.persistence.*;

@Serdeable
@Entity
@Table(name = "attributes")
public class Attribute {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    // STRING, TEXT, NUMBER(Float), DATE, BOOLEAN
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AttributeType type;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    public Attribute() {}

    public Attribute(String name, AttributeType type, Category category) {
        this.name = name;
        this.type = type;
        this.category = category;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public AttributeType getType() { return type; }
    public Category getCategory() { return category; }

    public void setId(Long id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setType(AttributeType type) { this.type = type; }
    public void setCategory(Category category) { this.category = category; }

}
