package practice.spring.springmvc.domain;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import practice.spring.springmvc.model.UnitOfMeasure;

public class UnitOfMeasureTest {

    UnitOfMeasure unitOfMeasure;

    @Before
    public void setUp(){
        unitOfMeasure = new UnitOfMeasure();
        unitOfMeasure.setId(1L);
        unitOfMeasure.setDescription("Test Unit");
        unitOfMeasure.setUom("Test");
    }

    @Test
    public void getIdTest() {
        Long idValue = 4L;

        unitOfMeasure.setId(idValue);
        Assert.assertEquals(idValue, unitOfMeasure.getId());
    }
}
