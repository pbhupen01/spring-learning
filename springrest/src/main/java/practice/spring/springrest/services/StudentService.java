package practice.spring.springrest.services;

import practice.spring.springrest.domain.Student;

import java.util.List;

public interface StudentService {

    public Student findById(Long id);

    public void deleteById(Long id);

    public Student createStudent(Student student);

    public Student updateStudent(Student student);

    public List<Student> getAllStudents();
}
