package com.example.student3.model;



import jakarta.persistence.*;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    @Id
    private String studentId;
    private String name;
    private String batch;
    private String emailId;
    private String mobile;
    private String CGPA;
}
