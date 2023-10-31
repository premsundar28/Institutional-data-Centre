package com.example.student3.repository;

import com.example.student3.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, String> {


    List<Student> findByCGPAGreaterThanEqual(String cgpa);


}
