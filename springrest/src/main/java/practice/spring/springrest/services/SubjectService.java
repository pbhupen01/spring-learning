package practice.spring.springrest.services;

import practice.spring.springrest.domain.Subject;

import java.util.List;

public interface SubjectService {

    public Subject findById(Long id);

    public void deleteById(Long id);

    public Subject createSubject(Subject student);

    public Subject updateSubject(Subject student);

    public List<Subject> getAllSubjects();
}
