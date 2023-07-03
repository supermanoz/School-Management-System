package com.sms.userservice.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeacherGradeHistoryPojo {

    private Long teacherGradeHistoryId;

    private Long teacherId;

    private String grade;

    private String date;
}
