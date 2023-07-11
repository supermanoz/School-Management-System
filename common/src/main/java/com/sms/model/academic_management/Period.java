package com.sms.model.academic_management;

import com.sms.enums.academic_management.PeriodEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Time;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Period {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long periodId;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PeriodEnum periodName;
    @Column(nullable = false)
    private Time beginsAt;
    @Column(nullable = false)
    private Time endsAt;
}
