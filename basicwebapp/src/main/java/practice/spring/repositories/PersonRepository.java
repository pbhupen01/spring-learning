package practice.spring.repositories;

import practice.spring.model.Person;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by jt on 5/16/17.
 */
public interface PersonRepository extends CrudRepository<Person, Long> {
}
