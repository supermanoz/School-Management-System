package com.sms.userservice.models;

import com.sms.model.user_management.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "students")
public class Student {

    @Id
    private Long studentId;

    @MapsId
    @OneToOne()
    @JoinColumn(name = "student_id", referencedColumnName = "user_id")
    private User user;

    private String currentGrade;
}
