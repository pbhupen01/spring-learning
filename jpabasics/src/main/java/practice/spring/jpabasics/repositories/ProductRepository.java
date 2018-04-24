package practice.spring.jpabasics.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import practice.spring.jpabasics.model.Product;

import java.util.Optional;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {

    Optional<Product> findByName(String name);
}
