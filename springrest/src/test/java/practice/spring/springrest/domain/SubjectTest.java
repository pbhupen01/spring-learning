package practice.spring.springrest.domain;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SubjectTest {

    private Subject subject;

    @Before
    public void setUp()
    {
        subject = new Subject();
    }

    @Test
    public void getIdTest()
    {
        subject.setId(123L);

        Assert.assertEquals(subject.getId(), Long.valueOf(123));
    }

    @After
    public void tearDown()
    {
        subject = null;
    }
}
