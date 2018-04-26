package practice.spring.springrest.controllers;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import practice.spring.springrest.controllers.v1.SubjectController;
import practice.spring.springrest.converter.SubjectDTOtoSubject;
import practice.spring.springrest.domain.Subject;
import practice.spring.springrest.dto.SubjectDTO;
import practice.spring.springrest.services.SubjectService;
import practice.spring.springrest.utils.ApplicationUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@WebMvcTest(SubjectController.class)
public class SubjectWebMvcTestControllerTest {

    @MockBean
    SubjectService subjectService;

    @Autowired
    SubjectDTOtoSubject subjectDTOtoSubject;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void givenIdFindSubjectTest() throws Exception
    {
        // given
        Long id = 1L;
        Subject subject = new Subject();
        subject.setId(id);
        subject.setName("Maths");
        Mockito.when(subjectService.findById(id)).thenReturn(subject);

        // when and then
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/subjects/{id}", 1))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is("Maths")));

        // then
        Mockito.verify(subjectService, Mockito.times(1)).findById(Mockito.anyLong());
    }


    @Test
    public void givenSubjectCreateSubjectTest() throws Exception
    {
        // given
        Long id = 1L;
        SubjectDTO dto = new SubjectDTO();
        dto.setId(id);
        dto.setName("Maths");
        Subject subject = subjectDTOtoSubject.convert(dto);
        Mockito.when(subjectService.findById(id)).thenReturn(null);
        Mockito.when(subjectService.createSubject(Mockito.any())).thenReturn(subject);

        // when and then
        mockMvc.perform(MockMvcRequestBuilders.post("/v1/subjects")
                .contentType(MediaType.APPLICATION_JSON)
                .content(ApplicationUtils.asJsonString(dto)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is("Maths")));

        // then
        Mockito.verify(subjectService, Mockito.times(1)).createSubject(Mockito.any());
    }

    @Test
    public void givenSubjectUpdateSubjectTest() throws Exception
    {
        // given
        SubjectDTO dto = new SubjectDTO();
        dto.setId(1L);
        dto.setName("Maths");
        Subject subject = subjectDTOtoSubject.convert(dto);
        Mockito.when(subjectService.updateSubject(Mockito.any())).thenReturn(subject);

        // when and then
        mockMvc.perform(MockMvcRequestBuilders.patch("/v1/subjects/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(ApplicationUtils.asJsonString(dto)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is("Maths")));

        // then
        Mockito.verify(subjectService, Mockito.times(1)).updateSubject(Mockito.any());
    }


    @Test
    public void givenIdDeleteSubjectTest() throws Exception
    {
        // given
        Long id = 1L;

        // when and then
        mockMvc.perform(MockMvcRequestBuilders.delete("/v1/subjects/{id}", 1))
                .andExpect(MockMvcResultMatchers.status().isOk());

        // then
        Mockito.verify(subjectService, Mockito.times(1)).deleteById(Mockito.anyLong());

    }

    @Test
    public void getListOfSubjectsTest() throws Exception
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

        // when and then
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/subjects/"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name", Matchers.is("Maths")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id", Matchers.is(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name", Matchers.is("Science")));

        // then
        Mockito.verify(subjectService, Mockito.times(1)).getAllSubjects();
    }
}
