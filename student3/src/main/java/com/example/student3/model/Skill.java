package com.example.student3.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "skill")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Skill {

    @Id
    @Column(name = "uniqueId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long uniqueId;

    @Column(name = "studentId")
    private String studentId;

    @Column(name = "skill")
    private String skill;

    @Column(name = "domain")
    private String domain;
}
