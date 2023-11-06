package com.example.student3.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Faculty {

    @Id
    @Column(name = "userId")
    private String userId;
    @Column(name = "name")
    private String name;
    @Column(name ="gender")
    private String gender;
    @Column(name = "dob")
    private Date dob;
    @Column(name = "department")
    private String department;
    @Column(name = "contactNumber")
    private String contactNumber;
    @Column(name = "address")
    private String address;
    @Column(name = "designation")
    private String designation;

    // Constructors, getters, and setters
}