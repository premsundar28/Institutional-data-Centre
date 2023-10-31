package com.example.student3.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.Date;

@Entity
@Table(name = "internship")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Internship {

    @Id
    @Column(name = "uniqueId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long uniqueId;

    @Column(name = "name")
    private String name;

    @Column(name = "company")
    private String company;

    @Column(name = "startDate")
    @Temporal(TemporalType.DATE)
    private Date startDate;

    @Column(name = "EndDate")
    @Temporal(TemporalType.DATE)
    private Date endDate;

    @Column(name = "studentid")
    private String studentId;

    @Column(name = "verification")
    private String verification;

    @Column(name = "domain")
    private String domain;
}
