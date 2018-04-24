package practice.spring.springmvc.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import practice.spring.springmvc.model.UnitOfMeasure;

import java.util.Optional;

@Repository
public interface UnitOfMeasureRepository extends CrudRepository<UnitOfMeasure, Long> {

    Optional<UnitOfMeasure> findByName(String uom);
}
