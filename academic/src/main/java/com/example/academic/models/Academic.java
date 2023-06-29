package com.example.academic.models;

import com.sms.model.user_management.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Academic {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long academicId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User userId;

    private Integer marks;

    private Date date;

    private String Subject;

    private String term;
}
