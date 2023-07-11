package com.sms.model.academic_management;

import com.sms.enums.academic_management.GradeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long courseId;
    @NotBlank
    @Column(nullable = false)
    private String subject;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private GradeEnum grade;
    @OneToOne
    @JoinColumn(name="period_id",nullable = false)
    private Period period;
}
