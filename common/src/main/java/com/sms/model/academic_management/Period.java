package com.sms.model.academic_management;

import com.sms.enums.academic_management.PeriodEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigInteger;
import java.sql.Date;
import java.sql.Time;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@NamedNativeQuery(query="select period_id as periodId,period_name as periodName,begins_at as beginsAt,ends_at as endsAt from period where begins_at<=current_timestamp() and ends_at>=current_timestamp()",
        name = "getCurrentPeriod",
        resultSetMapping = "Period.CurrentPeriod"
)
@SqlResultSetMapping(name="Period.CurrentPeriod",classes = {
        @ConstructorResult(targetClass = Period.class, columns ={
                @ColumnResult(name="periodID"),
                @ColumnResult(name="periodName"),
                @ColumnResult(name="beginsAt"),
                @ColumnResult(name="endsAt")
        })})
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
    public Period(BigInteger periodId, String periodName, Object beginsAt, Object endsAt){
        this.periodId=periodId.longValue();
        this.periodName=PeriodEnum.valueOf(periodName);
        this.beginsAt= (Time)beginsAt;
        this.endsAt= (Time)endsAt;
    }
}
