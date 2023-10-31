package com.example.student3.repository;

import com.example.student3.model.Social;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SocialRepository extends JpaRepository<Social,Long> {
    Social findByUserId(String userId);
}
