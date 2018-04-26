package practice.spring.springrest.services;

import practice.spring.springrest.domain.Subject;

import java.util.List;

public interface SubjectService {

    public Subject findById(Long id);

    public void deleteById(Long id);

    public Subject createSubject(Subject subject);

    public Subject updateSubject(Subject subject);

    public List<Subject> getAllSubjects();
}
