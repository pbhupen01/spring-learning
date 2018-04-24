package practice.spring.springmvc.repositories;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import practice.spring.springmvc.model.UnitOfMeasure;

import java.util.Optional;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UnitOfMeasureRepositoryTest {

    @Autowired
    UnitOfMeasureRepository unitOfMeasureRepository;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void findByUom(){
        Optional<UnitOfMeasure> uomOptional = unitOfMeasureRepository.findByUom("Length");

        Assert.assertEquals("Length", uomOptional.get().getDescription());
    }

}
