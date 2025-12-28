package org.example.sbunittesting.service.impl;

import org.example.sbunittesting.entity.Student;
import org.example.sbunittesting.repo.StudentRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class StudentServiceImplTest {
    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentServiceImpl studentService;
    private Student expectedStudent;

    @BeforeEach
    void setUp() {
        expectedStudent = new Student(
                1,
                "Grant Cardone"
        );
    }


    @Test
    public void getOneStudent() {
        int studentId = 1;
        Mockito.when(studentRepository.findById(studentId)).thenReturn(Optional.of(expectedStudent));

        Student resultStudent = studentService.getOneStudent(studentId);

        Assertions.assertEquals(studentId, resultStudent.getId());
        Assertions.assertEquals(expectedStudent.getName(), resultStudent.getName());
        Mockito.verify(studentRepository, Mockito.times(1)).findById(studentId);
    }

    @Test
    public void getOneEmptyStudent() {
        int studentId = 1;
        Mockito.when(studentRepository.findById(studentId)).thenReturn(Optional.empty());
        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            studentService.getOneStudent(studentId);
        });
        Assertions.assertEquals("Student not found by ID : 1", exception.getMessage());  // ✅
    }

    @Test
    void getAllStudents() {
        List<Student> expectedStudents = List.of(
                new Student(2, "Olma"),
                new Student(3, "Dinara")
        );
        Mockito.when(studentRepository.findAll()).thenReturn(expectedStudents);
        List<Student> allStudents = studentService.getAllStudents();
        Assertions.assertEquals(expectedStudents, allStudents);
    }

    @Test
    void saveStudent() {
        Student inputStudent = new Student(1, "Grant Cardone");
        Mockito.when(studentRepository.save(inputStudent)).thenReturn(expectedStudent);
        Student result = studentService.saveStudent(inputStudent);
        Assertions.assertNotNull(result);
        Assertions.assertEquals(inputStudent.getId(), result.getId());
        Assertions.assertEquals(inputStudent.getName(), result.getName());
    }


    @Test
    void updateStudent() {
        int studentId = 1;
        Student newStudent = new Student(null, "Elena Cardone");
        Mockito.when(studentRepository.findById(studentId)).thenReturn(Optional.of(expectedStudent));
        Mockito.when(studentRepository.save(expectedStudent)).thenReturn(expectedStudent);
        Student udpatedRes = studentService.updateStudent(newStudent, studentId);
        Assertions.assertNotNull(udpatedRes);
        Assertions.assertEquals(studentId, udpatedRes.getId());
        Assertions.assertEquals(newStudent.getName(), expectedStudent.getName());

    }
}
