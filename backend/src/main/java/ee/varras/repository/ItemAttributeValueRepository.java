package ee.varras.repository;

import ee.varras.model.ItemAttributeValue;
import io.micronaut.data.annotation.Repository;

@Repository
public class ItemAttributeValueRepository {
    public void deleteAllByItemId(Long itemId) {}

    public Iterable<? extends ItemAttributeValue> findAllByItemId(Long id) {
        return null;
    }
}
