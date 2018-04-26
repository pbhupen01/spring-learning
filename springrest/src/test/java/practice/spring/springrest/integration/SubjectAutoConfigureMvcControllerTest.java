package practice.spring.springrest.integration;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import practice.spring.springrest.converter.SubjectDTOtoSubject;
import practice.spring.springrest.domain.Subject;
import practice.spring.springrest.dto.SubjectDTO;
import practice.spring.springrest.services.SubjectService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
public class SubjectAutoConfigureMvcControllerTest {

    @LocalServerPort
    private int port;

    @MockBean
    SubjectService subjectService;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    SubjectDTOtoSubject subjectDTOtoSubject;

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
        ResponseEntity<SubjectDTO> responseEntity = this.testRestTemplate.getForEntity("http://localhost:" + port + "/v1/subjects/1", SubjectDTO.class);

        // then
        Mockito.verify(subjectService, Mockito.times(1)).findById(Mockito.anyLong());
        Assert.assertNotNull(responseEntity);
        Assert.assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
        SubjectDTO respDto = responseEntity.getBody();
        Assert.assertNotNull(respDto);
        Assert.assertEquals(respDto.getId(), subject.getId());
        Assert.assertEquals(respDto.getName(), subject.getName());
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
        ResponseEntity<SubjectDTO> responseEntity = this.testRestTemplate.postForEntity("http://localhost:" + port + "/v1/subjects", dto, SubjectDTO.class);

        // then
        Mockito.verify(subjectService, Mockito.times(1)).createSubject(Mockito.any());
        Assert.assertNotNull(responseEntity);
        Assert.assertEquals(responseEntity.getStatusCode(), HttpStatus.CREATED);
        SubjectDTO respDto = responseEntity.getBody();
        Assert.assertNotNull(respDto);
        Assert.assertEquals(respDto.getId(), dto.getId());
        Assert.assertEquals(respDto.getName(), dto.getName());
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
        SubjectDTO respDto = this.testRestTemplate.patchForObject("http://localhost:" + port + "/v1/subjects", dto, SubjectDTO.class);

        // then
        Mockito.verify(subjectService, Mockito.times(1)).updateSubject(Mockito.any());
        Assert.assertNotNull(respDto);
        Assert.assertEquals(respDto.getId(), dto.getId());
        Assert.assertEquals(respDto.getName(), dto.getName());
    }


    @Test
    public void givenIdDeleteSubjectTest()
    {
        // given
        Long id = 1L;

        // when
        this.testRestTemplate.delete("http://localhost:" + port + "/v1/subjects/1");

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
        ResponseEntity<List> responseEntity = this.testRestTemplate.getForEntity("http://localhost:" + port + "/v1/subjects", List.class);

        // then
        Mockito.verify(subjectService, Mockito.times(1)).getAllSubjects();
        Assert.assertEquals(responseEntity .getStatusCode(), HttpStatus.OK);
        List<SubjectDTO> respDtos = (List<SubjectDTO>) responseEntity.getBody();
        Assert.assertNotNull(respDtos);
        Assert.assertEquals(respDtos.size(), subjects.size());
    }
}
