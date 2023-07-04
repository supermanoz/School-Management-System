package com.sms.attendanceservice.model;
import com.sms.model.user_management.User;
import lombok.*;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long attendanceId;
    private LocalDateTime checkIn;
    private LocalDateTime checkOut;
    private String createdBy;
    private String updatedBy;

    @OneToMany
    @JoinTable(name = "user_attendance")
    @JoinColumn(name = "userId")
    private List<User> users;


}

