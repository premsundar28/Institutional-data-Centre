package com.example.project.Repository;

import com.example.project.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student,String> {

    @Query("SELECT s.rollNo FROM Student s INNER JOIN s.internships i WHERE i.role = :role")
    List<String> findRollNumbersByInternshipRole(@Param("role") String role);

    @Query("SELECT DISTINCT s.rollNo FROM Student s JOIN s.skills skill WHERE skill IN :skills")
    List<String> findRollNumbersBySkills(@Param("skills") List<String> skills);

    @Query("SELECT DISTINCT s.rollNo FROM Student s JOIN s.internships i WHERE i.company = :company")
    List<String> findRollNumbersByInternshipCompany(@Param("company") String company);

    @Query("SELECT DISTINCT s.rollNo FROM Student s WHERE s.cgpa >= :cgpa")
    List<String> findRollNumbersByCGPA(@Param("cgpa") Double cgpa);

    @Query("SELECT DISTINCT s.rollNo FROM Student s JOIN s.certificates cert WHERE cert.issuer = :issuer")
    List<String> findRollNumbersByCertificateIssuer(@Param("issuer") String issuer);


}
