package practice.spring.jpabasics.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import practice.spring.jpabasics.model.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
}
