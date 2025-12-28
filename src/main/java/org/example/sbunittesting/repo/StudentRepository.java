package org.example.sbunittesting.repo;

import org.example.sbunittesting.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Integer> {
}