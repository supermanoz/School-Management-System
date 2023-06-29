package com.example.academic.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Academic {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long academicId;

    private String userId;
}
