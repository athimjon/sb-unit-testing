package org.example.sbunittesting;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;


    public Student getOneStudent(Integer id) {
        Student student = studentRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Student not found by ID : " + id));
        return student;
    }
}
