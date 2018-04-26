package practice.spring.springrest.services;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;
import practice.spring.springrest.domain.Subject;
import practice.spring.springrest.repositories.SubjectRepository;

import javax.persistence.EntityExistsException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SubjectServiceImplTest {

    @Mock
    SubjectRepository subjectRepository;

    @InjectMocks
    SubjectServiceImpl subjectServiceImpl;

    @Before
    public void setUp()
    {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void givenIdFindSubjectTest() {

        // given
        Subject subject = new Subject();
        subject.setId(Long.valueOf(1));
        subject.setName("Maths");
        Mockito.when(subjectRepository.findById(subject.getId())).thenReturn(java.util.Optional.ofNullable(subject));
        ArgumentCaptor<Long> captor = ArgumentCaptor.forClass(Long.class);

        // when
        Subject result = subjectServiceImpl.findById(subject.getId());

        // then
        Mockito.verify(subjectRepository, Mockito.times(1)).findById(captor.capture());
        Assert.assertEquals(captor.getValue(), Long.valueOf(1));
        Assert.assertNotNull(result);
        Assert.assertEquals(result.getId(), subject.getId());
        Assert.assertEquals(result.getName(), subject.getName());

    }

    @Test
    public void givenIdDeleteSubjectTest() {

        // given
        Subject subject = new Subject();
        subject.setId(Long.valueOf(1));
        subject.setName("Maths");
        Mockito.when(subjectRepository.findById(subject.getId())).thenReturn(java.util.Optional.ofNullable(subject));

        // when
        subjectServiceImpl.deleteById(subject.getId());

        // then
        Mockito.verify(subjectRepository, Mockito.times(1)).findById(Mockito.anyLong());
        Mockito.verify(subjectRepository, Mockito.times(1)).deleteById(Mockito.anyLong());
    }

    @Test
    public void givenSubjectDTOcreateSubjectTest() {

        // given
        Subject subject = new Subject();
        subject.setId(Long.valueOf(1));
        subject.setName("Maths");
        Mockito.when(subjectRepository.findById(subject.getId())).thenReturn(Optional.empty());
        Mockito.when(subjectRepository.save(Mockito.any())).thenReturn(subject);

        // when
        Subject result = subjectServiceImpl.createSubject(subject);

        // then
        Mockito.verify(subjectRepository, Mockito.times(1)).findById(Mockito.anyLong());
        Mockito.verify(subjectRepository, Mockito.times(1)).save(Mockito.any());
        Assert.assertNotNull(result);
        Assert.assertEquals(result.getId(), subject.getId());
        Assert.assertEquals(result.getName(), subject.getName());
    }

    @Test(expected = EntityExistsException.class)
    public void givenSubjectExistsCreateSubjectTest() {

        // given
        Subject subject = new Subject();
        subject.setId(Long.valueOf(1));
        subject.setName("Maths");
        Mockito.when(subjectRepository.findById(subject.getId())).thenReturn(Optional.of(subject));
        Mockito.when(subjectRepository.save(Mockito.any())).thenReturn(subject);

        // when
        Subject result = subjectServiceImpl.createSubject(subject);

        // then
        Mockito.verify(subjectRepository, Mockito.times(1)).findById(Mockito.anyLong());
    }

    @Test
    public void givenSubjectDTOUpdateSubjectTest() {

        // given
        Subject subject = new Subject();
        subject.setId(Long.valueOf(1));
        subject.setName("Maths");
        Mockito.when(subjectRepository.findById(subject.getId())).thenReturn(Optional.of(subject));
        Mockito.when(subjectRepository.save(Mockito.any())).thenReturn(subject);

        // when
        Subject result = subjectServiceImpl.updateSubject(subject);

        // then
        Mockito.verify(subjectRepository, Mockito.times(1)).findById(Mockito.anyLong());
        Mockito.verify(subjectRepository, Mockito.times(1)).save(Mockito.any());
        Assert.assertNotNull(result);
        Assert.assertEquals(result.getId(), subject.getId());
        Assert.assertEquals(result.getName(), subject.getName());
    }

    @Test
    public void getAllSubjectsTest() {

        // given
        Subject subject = new Subject();
        subject.setId(Long.valueOf(1));
        subject.setName("Maths");
        List<Subject> subjects = new ArrayList();
        subjects.add(subject);
        Mockito.when(subjectRepository.findAll()).thenReturn(subjects);

        // when
        List result = subjectServiceImpl.getAllSubjects();

        // then
        Mockito.verify(subjectRepository, Mockito.times(1)).findAll();
        Assert.assertNotNull(result);
        Assert.assertEquals(result.size(), subjects.size());


    }
}