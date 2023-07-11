package com.sms.pojo;

import com.sms.enums.user_management.PeriodEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PeriodPojo {
    private Long periodId;
    private PeriodEnum periodName;
    private Time beginsAt;
    private Time endsAt;
}
