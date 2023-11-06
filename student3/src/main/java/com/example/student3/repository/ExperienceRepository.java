package com.example.student3.repository;

import com.example.student3.model.Experience;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExperienceRepository extends JpaRepository<Experience,String> {
    List<Experience> findByUserId(String userId);
}
