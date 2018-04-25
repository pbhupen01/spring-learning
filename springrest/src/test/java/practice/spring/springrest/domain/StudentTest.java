package practice.spring.springrest.domain;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class StudentTest {

    Student student;

    @Before
    public void setUp()
    {
        student = new Student();
    }

    @Test
    public void getFirstNameTest()
    {
        student.setFirstName("ABC");

        Assert.assertEquals(student.getFirstName(), "ABC");
    }

    @After
    public void tearDown()
    {
        student = null;
    }
}
