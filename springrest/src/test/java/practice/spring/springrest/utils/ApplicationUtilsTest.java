package practice.spring.springrest.utils;

import org.junit.Assert;
import org.junit.Test;
import practice.spring.springrest.domain.Subject;

import static org.junit.Assert.*;

public class ApplicationUtilsTest {



    @Test
    public void asJsonString() {

        // given
        Subject dto = new Subject();
        dto.setId(Long.valueOf(10));
        dto.setName("Maths");

        // when
        String json = ApplicationUtils.asJsonString(dto);

        // then
        Assert.assertEquals(json, "{\"id\":10,\"name\":\"Maths\"}");

    }
}