package org.example.sbunittesting;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

public class StudentServiceTest {

    @Test
    public void getOneStudent() {
        StudentRepository studentRepository = Mockito.mock(StudentRepository.class);
        StudentService studentService = new StudentService(studentRepository);
        int studentId = 1;
        Mockito.when(studentRepository.findById(studentId)).thenReturn(Optional.of(new Student(studentId, "Dummy Obj", 45, "+999989999999")));
        Student oneStudent = studentService.getOneStudent(studentId);
        Assertions.assertEquals(studentId, oneStudent.getId());
    }

    @Test
    public void getOneEmptyStudent() {
        StudentRepository studentRepository = Mockito.mock(StudentRepository.class);
        StudentService studentService = new StudentService(studentRepository);

        int studentId = 1;
        Mockito.when(studentRepository.findById(studentId)).thenReturn(Optional.empty());
        Assertions.assertThrows(RuntimeException.class, () -> {
            studentService.getOneStudent(studentId);
        });

    }
}
