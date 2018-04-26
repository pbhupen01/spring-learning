package practice.spring.springrest.controllers;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import practice.spring.springrest.controllers.v1.SubjectController;
import practice.spring.springrest.domain.Subject;
import practice.spring.springrest.dto.SubjectDTO;
import practice.spring.springrest.services.SubjectService;

import java.util.List;

public class SubjectBasicControllerTest {

    @Mock
    SubjectService subjectService;

    private SubjectController subjectController;

    @Before
    public void setUp()
    {
        subjectController = new SubjectController(subjectService);
    }

    @Test
    public void givenIdFindSubjectTest()
    {
        // given
        Long id = 1L;

        // when
        ResponseEntity<SubjectDTO> subject = subjectController.findSubjectById(id);

        // then
        Assert.assertNotNull(subject);
    }


    @Test
    public void givenSubjectCreateSubjectTest()
    {
        // given
        SubjectDTO subject = new SubjectDTO();
        subject.setId(1L);
        subject.setName("Maths");

        // when
        ResponseEntity<SubjectDTO> createdSubject = subjectController.createSubject(subject);

        // then
        Assert.assertNotNull(createdSubject);
    }

    @Test
    public void givenSubjectUpdateSubjectTest()
    {
        // given
        SubjectDTO subject = new SubjectDTO();
        subject.setId(1L);
        subject.setName("Maths");

        // when
        ResponseEntity<SubjectDTO> updatedSubject = subjectController.updateSubject(subject);

        // then
        Assert.assertNotNull(updatedSubject);
    }


    @Test
    public void givenIdDeleteSubjectTest()
    {
        // given
        Long id = 1L;

        // when
        subjectController.deleteSubjectById(id);

        // then

    }

    @Test
    public void getListOfSubjectsTest()
    {

        // when
        ResponseEntity<List<SubjectDTO>> subject = subjectController.getSubjects();

        // then
        Assert.assertEquals(subject.getStatusCode(), HttpStatus.OK);
    }


}
