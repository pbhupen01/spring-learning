package practice.spring.springrest.controllers;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;
import org.mockito.internal.configuration.injection.MockInjection;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import practice.spring.springrest.controllers.v1.SubjectController;
import practice.spring.springrest.converter.SubjectDTOtoSubject;
import practice.spring.springrest.converter.SubjectToSubjectDTO;
import practice.spring.springrest.domain.Student;
import practice.spring.springrest.domain.Subject;
import practice.spring.springrest.dto.SubjectDTO;
import practice.spring.springrest.services.SubjectService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SubjectBasicControllerTest {

    @Mock
    SubjectService subjectService;

    @Spy
    SubjectDTOtoSubject subjectDTOtoSubject;

    @Spy
    SubjectToSubjectDTO subjectToSubjectDTO;

    @InjectMocks
    private SubjectController subjectController;

    @Before
    public void setUp()
    {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void givenIdFindSubjectTest()
    {
        // given
        Long id = 1L;
        Subject subject = new Subject();
        subject.setId(id);
        subject.setName("Maths");
        Mockito.when(subjectService.findById(id)).thenReturn(subject);

        // when
        ResponseEntity<SubjectDTO> responseEntity = subjectController.findSubjectById(id);

        // then
        Mockito.verify(subjectService, Mockito.times(1)).findById(Mockito.anyLong());
        Assert.assertNotNull(responseEntity);
        Assert.assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
        SubjectDTO dto = responseEntity.getBody();
        Assert.assertNotNull(dto);
        Assert.assertEquals(dto.getId(), subject.getId());
        Assert.assertEquals(dto.getName(), subject.getName());
    }


    @Test
    public void givenSubjectCreateSubjectTest()
    {
        // given
        Long id = 1L;
        SubjectDTO dto = new SubjectDTO();
        dto.setId(id);
        dto.setName("Maths");
        Subject subject = subjectDTOtoSubject.convert(dto);
        Mockito.when(subjectService.findById(id)).thenReturn(null);
        Mockito.when(subjectService.createSubject(Mockito.any())).thenReturn(subject);

        // when
        ResponseEntity<SubjectDTO> responseEntity = subjectController.createSubject(dto);

        // then
        Mockito.verify(subjectService, Mockito.times(1)).createSubject(Mockito.any());
        Assert.assertNotNull(responseEntity);
        Assert.assertEquals(responseEntity.getStatusCode(), HttpStatus.CREATED);
        SubjectDTO respDto = responseEntity.getBody();
        Assert.assertNotNull(dto);
        Assert.assertEquals(dto.getId(), respDto.getId());
        Assert.assertEquals(dto.getName(), respDto.getName());
    }

    @Test
    public void givenSubjectUpdateSubjectTest()
    {
        // given
        SubjectDTO dto = new SubjectDTO();
        dto.setId(1L);
        dto.setName("Maths");
        Subject subject = subjectDTOtoSubject.convert(dto);
        Mockito.when(subjectService.updateSubject(Mockito.any())).thenReturn(subject);

        // when
        ResponseEntity<SubjectDTO> responseEntity = subjectController.updateSubject(dto);

        // then
        Mockito.verify(subjectService, Mockito.times(1)).updateSubject(Mockito.any());
        Assert.assertNotNull(responseEntity);
        Assert.assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
        SubjectDTO respDto = responseEntity.getBody();
        Assert.assertNotNull(dto);
        Assert.assertEquals(dto.getId(), respDto.getId());
        Assert.assertEquals(dto.getName(), respDto.getName());
    }


    @Test
    public void givenIdDeleteSubjectTest()
    {
        // Can be properly tested only when used with springboot

        // given
        Long id = 1L;

        // when
        subjectController.deleteSubjectById(id);

        // then
        Mockito.verify(subjectService, Mockito.times(1)).deleteById(Mockito.anyLong());

    }

    @Test
    public void getListOfSubjectsTest()
    {
        // given
        List<SubjectDTO> dtos = new ArrayList<>();
        SubjectDTO dto1 = new SubjectDTO();
        dto1.setId(1L);
        dto1.setName("Maths");
        SubjectDTO dto2 = new SubjectDTO();
        dto2.setId(2L);
        dto2.setName("Science");
        dtos.add(dto1);
        dtos.add(dto2);
        List<Subject> subjects = dtos.stream().map(subjectDTOtoSubject::convert).collect(Collectors.toList());
        Mockito.when(subjectService.getAllSubjects()).thenReturn(subjects);

        // when
        ResponseEntity<List<SubjectDTO>> responseEntity = subjectController.getSubjects();

        // then
        Mockito.verify(subjectService, Mockito.times(1)).getAllSubjects();
        Assert.assertEquals(responseEntity .getStatusCode(), HttpStatus.OK);
        Assert.assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
        List<SubjectDTO> respDtos = responseEntity.getBody();
        Assert.assertNotNull(respDtos);
        Assert.assertEquals(respDtos.size(), subjects.size());
    }


}
