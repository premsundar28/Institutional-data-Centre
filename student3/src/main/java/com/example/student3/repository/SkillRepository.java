package com.example.student3.repository;
import java.util.List;
import com.example.student3.model.Skill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SkillRepository extends JpaRepository<Skill,String> {
    List<Skill> findBySkill(String skill);
}
