package practice.spring.springrest.services;

import org.springframework.stereotype.Service;
import practice.spring.springrest.domain.Subject;

import java.util.List;

@Service
public class SubjectServiceImpl implements  SubjectService{
    @Override
    public Subject findById(Long id) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public Subject createSubject(Subject student) {
        return null;
    }

    @Override
    public Subject updateSubject(Subject student) {
        return null;
    }

    @Override
    public List<Subject> getAllSubjects() {
        return null;
    }
}
