package com.sms.academicservice.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SubjectResult {
    private Long academicId;
    private String subject;
    private Integer marks;
}
