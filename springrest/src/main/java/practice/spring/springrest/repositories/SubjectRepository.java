package practice.spring.springrest.repositories;

import org.springframework.data.repository.CrudRepository;
import practice.spring.springrest.domain.Subject;

public interface SubjectRepository extends CrudRepository<Subject, Long> {
}
