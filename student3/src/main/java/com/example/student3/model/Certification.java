package com.example.student3.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "certification")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Certification {

    @Id
    @Column(name = "studentId")
    private String studentId;

    @Column(name = "name")
    private String name;

    @Column(name = "expire")
    @Temporal(TemporalType.DATE)
    private Date expire;

    @Column(name = "verification")
    private String verification;

    @Column(name = "certify")
    private String certify;
}
