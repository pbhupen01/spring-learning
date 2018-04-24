package practice.spring.jpabasics.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import practice.spring.jpabasics.model.Category;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Long> {
}
