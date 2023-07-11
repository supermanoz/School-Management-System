package com.sms.attendanceservice.model;
import lombok.*;
import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Attendance implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long attendanceId;

    private Long userId;
    private LocalDateTime checkIn;
    private LocalDateTime checkOut;
    private String period;

}

