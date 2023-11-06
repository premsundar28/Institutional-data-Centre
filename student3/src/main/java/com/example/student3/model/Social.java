package com.example.student3.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Social {

    @Id
    @Column(name = "UserId")
    private String userId;
    private String github;
    private String linkedin;


}
