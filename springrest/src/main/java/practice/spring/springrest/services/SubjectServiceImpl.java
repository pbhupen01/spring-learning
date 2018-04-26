package practice.spring.springrest.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import practice.spring.springrest.domain.Subject;
import practice.spring.springrest.repositories.SubjectRepository;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SubjectServiceImpl implements  SubjectService{

    SubjectRepository subjectRepository;

    @Autowired
    public SubjectServiceImpl(SubjectRepository subjectRepository)
    {
        this.subjectRepository = subjectRepository;
    }

    @Override
    public Subject findById(Long id) {
        Subject subject = searchById(id);
        if(subject == null)
        {
            throw new EntityNotFoundException(String.format("Subject with ID %d not found.", id));
        }
        return subject;
    }

    @Override
    public void deleteById(Long id) {
        Subject subject = searchById(id);
        if(subject == null)
        {
            throw new EntityNotFoundException(String.format("Subject with ID %d not found.", id));
        }
        subjectRepository.deleteById(id);
    }

    @Override
    public Subject createSubject(Subject subject) {
        Subject searchResult = searchById(subject.getId());
        if(searchResult != null)
        {
            throw new EntityExistsException(String.format("Subject with ID %d already exists.", subject.getId()));
        }
        return subjectRepository.save(subject);
    }

    @Override
    public Subject updateSubject(Subject subject) {
        Subject searchResult = searchById(subject.getId());
        if(searchResult == null)
        {
            throw new EntityNotFoundException(String.format("Subject with ID %d not found.", subject.getId()));
        }
        return subjectRepository.save(subject);
    }

    @Override
    public List<Subject> getAllSubjects() {
        List<Subject> subjects = new ArrayList();
        subjectRepository.findAll().forEach(subjects::add);
        return subjects;
    }


    private Subject searchById(Long id)
    {
        Optional<Subject> optionalResult = subjectRepository.findById(id);

        if(optionalResult.isPresent())
        {
            return optionalResult.get();
        }
        return null;
    }
}
