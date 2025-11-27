package ee.varras.repository;

import ee.varras.model.Item;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

@Repository
public interface ItemRepository extends CrudRepository<Item, Long> {
    Iterable<Item> findAllByCategoryId(Long categoryId);
}
