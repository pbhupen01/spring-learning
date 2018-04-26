package practice.spring.springrest.repositories;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import practice.spring.springrest.domain.Subject;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class SubjectRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private SubjectRepository subjectRepository;

    @Test
    public void saveSubjectTest()
    {
        // given
        Long id = 1L;
        Subject subject = new Subject();
        subject.setId(id);
        subject.setName("Maths");

        // when
        Subject result = subjectRepository.save(subject);

        // then
        Assert.assertNotNull(result);
        Assert.assertEquals(result.getId(), subject.getId());
        Assert.assertEquals(result.getName(), subject.getName());
    }

    @Test
    public void findSubjectTest()
    {
        // given
        Long id = 2L;
        Subject subject = new Subject();
        subject.setId(id);
        subject.setName("Science");
        //this.entityManager.persist(subject);
        subjectRepository.save(subject);

        // when
        Subject result = subjectRepository.findById(subject.getId()).get();

        // then
        Assert.assertNotNull(result);
        Assert.assertEquals(result.getId(), subject.getId());
        Assert.assertEquals(result.getName(), subject.getName());
    }

}