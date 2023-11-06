package com.example.student3.repository;

import com.example.student3.model.Internship;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InternshipRepository extends JpaRepository<Internship,String> {
    List<Internship> findByDomain(String domain);
}
