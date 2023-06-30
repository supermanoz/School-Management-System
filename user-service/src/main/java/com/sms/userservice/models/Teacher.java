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
@Table(name = "teachers")
public class Teacher {

    @Id
    private Long teacherId;

    @MapsId
    @OneToOne()
    @JoinColumn(name = "teacher_id", referencedColumnName = "user_id")
    private User user;

    private Integer currentSalary;
    private String assignedGrade;
}
