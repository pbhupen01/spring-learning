package practice.spring.springrest.converter;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import practice.spring.springrest.domain.Subject;
import practice.spring.springrest.dto.SubjectDTO;

import static org.junit.Assert.*;

public class SubjectDTOtoSubjectTest {

    SubjectDTOtoSubject subjectDTOtoSubject;

    @Before
    public void setUp()
    {
        this.subjectDTOtoSubject = new SubjectDTOtoSubject();
    }

    @Test
    public void givenSubjectDTOThenConvertToSubjectTest() {

        // given
        SubjectDTO dto = new SubjectDTO();
        dto.setId(1L);
        dto.setName("Math");

        // when
        Subject subject = subjectDTOtoSubject.convert(dto);

        // then
        Assert.assertNotNull(subject);
        Assert.assertEquals(subject.getId(), dto.getId());
        Assert.assertEquals(subject.getName(), dto.getName());
    }

    @After
    public void tearDown()
    {
        this.subjectDTOtoSubject = null;
    }
}