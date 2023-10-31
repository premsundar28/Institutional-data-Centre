package com.example.student3.repository;

import com.example.student3.model.RecentEducation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecentEducationRepository extends JpaRepository<RecentEducation,Long> {
    RecentEducation findByUserId(String userId);
}
