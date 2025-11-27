package ee.varras.repository;

import ee.varras.model.Attribute;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;

import java.util.List;

@Repository
public interface AttributeRepository extends JpaRepository<Attribute, Long> {

    List<Attribute> findByCategoryId(Long categoryId);

    List<Attribute> findAllByCategoryId(Long categoryId);
}
