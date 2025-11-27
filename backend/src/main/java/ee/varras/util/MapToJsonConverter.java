package ee.varras.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.HashMap;
import java.util.Map;

@Converter
public class MapToJsonConverter implements AttributeConverter<Map<String, Object>, String> {

    private static final ObjectMapper mapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(Map<String, Object> attribute) {
        try {
            if (attribute == null) return null;
            return mapper.writeValueAsString(attribute);
        } catch (Exception e) {
            throw new RuntimeException("JSON serialization error", e);
        }
    }

    @Override
    public Map<String, Object> convertToEntityAttribute(String dbData) {
        try {
            if (dbData == null || dbData.isEmpty()) return new HashMap<>();
            return mapper.readValue(dbData, Map.class);
        } catch (Exception e) {
            throw new RuntimeException("JSON deserialization error", e);
        }
    }
}
