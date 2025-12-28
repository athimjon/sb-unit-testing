package org.example.sbunittesting.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.sbunittesting.entity.Student;
import org.example.sbunittesting.repo.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl {

    private final StudentRepository studentRepository;


    public Student getOneStudent(Integer id) {
        Student student = studentRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Student not found by ID : " + id));
        return student;
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student saveStudent(Student student) {
        student.setName("sdsfs");
        return studentRepository.save(student);
    }

    public Student updateStudent(Student student, Integer id) {
        Student dbStudent = studentRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Student Not Found by id : " + id));

        dbStudent.setName(student.getName());
        return studentRepository.save(dbStudent);
    }
}
