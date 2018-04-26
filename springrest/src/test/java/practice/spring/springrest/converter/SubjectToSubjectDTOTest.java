package practice.spring.springrest.converter;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import practice.spring.springrest.domain.Subject;
import practice.spring.springrest.dto.SubjectDTO;

import static org.junit.Assert.*;

public class SubjectToSubjectDTOTest {

    SubjectToSubjectDTO subjectToSubjectDTO;

    @Before
    public void setUp()
    {
        this.subjectToSubjectDTO = new SubjectToSubjectDTO();
    }

    @Test
    public void givenSubjectThenConvertToSubjectDTOTest() {

        // given
        Subject subject = new Subject();
        subject.setId(1L);
        subject.setName("Math");

        // when
        SubjectDTO dto = subjectToSubjectDTO.convert(subject);

        // then
        Assert.assertNotNull(dto);
        Assert.assertEquals(dto.getId(), subject.getId());
        Assert.assertEquals(dto.getName(), subject.getName());
    }

    @After
    public void tearDown()
    {
        this.subjectToSubjectDTO = null;
    }
}