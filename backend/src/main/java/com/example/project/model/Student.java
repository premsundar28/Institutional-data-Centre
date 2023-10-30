package com.example.project.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "student")
public class Student {

    @Id
    private String rollNo;

    @Column(name = "name")
    private String name;

    @Column(name = "department")
    private String department;

    @Column(name = "valid_up_to")
    private int validUpTo;

    @Column(name = "mail_id")
    private String mailId;

    @Column(name = "cgpa")
    private Double cgpa;

    @Column(name = "linkedin")
    private String linkedin;

    @Column(name = "github")
    private String github;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "roll_no", referencedColumnName = "rollNo")
    private List<Certificates> certificates;

    @ElementCollection
    @CollectionTable(name = "projects", joinColumns = @JoinColumn(name = "roll_no"))
    @Column(name = "project")
    private List<String> projects;

    @ElementCollection
    @CollectionTable(name = "skills", joinColumns = @JoinColumn(name = "roll_no"))
    @Column(name = "skill")
    private List<String> skills;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "roll_no", referencedColumnName = "rollNo")
    private List<Internship> internships;
}
