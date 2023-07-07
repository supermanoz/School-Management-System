package com.sms.attendanceservice.model;
import com.sms.model.user_management.User;
import lombok.*;
import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

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

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
    private LocalDateTime checkIn;
    private LocalDateTime checkOut;
    private String subjectCode;

}

