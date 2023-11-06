package com.example.student3.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Experience {

    @Id
    @Column(name = "userId")
    private String userId;

    private String experienceType;
    private Date exFrom;
    private Date exTo;
    private String company;
    private String designation;


}