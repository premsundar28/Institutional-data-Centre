package com.example.student3.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecentEducation {

    @Id
    @Column(name = "userId")
    private String userId;

    private String type;
    private String issuedBy;


}
