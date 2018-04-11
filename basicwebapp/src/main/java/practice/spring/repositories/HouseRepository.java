package practice.spring.repositories;

import practice.spring.model.House;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by jt on 5/16/17.
 */
public interface HouseRepository extends CrudRepository<House, Long> {
}
