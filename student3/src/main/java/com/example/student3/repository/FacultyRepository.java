package com.example.student3.repository;

import com.example.student3.model.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FacultyRepository extends JpaRepository<Faculty,String> {
}
