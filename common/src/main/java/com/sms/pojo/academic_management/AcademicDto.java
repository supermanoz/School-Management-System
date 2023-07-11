package com.sms.pojo.academic_management;
import java.math.BigInteger;
import java.util.Date;

public class AcademicDto {
    private Long academicId;
    private Date date;
    private Integer marks;
    private String term;
    private String subject;
    private Long userId;
    private String grade;

    public AcademicDto(Long academicId, Date date, Integer marks, String term, String subject, Long userId, String grade) {
        this.academicId = academicId;
        this.date = date;
        this.marks = marks;
        this.term = term;
        this.subject = subject;
        this.userId = userId;
        this.grade = grade;
    }

    public AcademicDto(BigInteger academicId, Date date, Integer marks, String term, String subject, BigInteger userId, String grade) {
        this.academicId = academicId.longValue();
        this.date = date;
        this.marks = marks;
        this.term = term;
        this.subject = subject;
        this.userId = userId.longValue();
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "AcademicDto{" +
                "academicId=" + academicId +
                ", date=" + date +
                ", marks=" + marks +
                ", term='" + term + '\'' +
                ", subject='" + subject + '\'' +
                ", userId=" + userId +
                ", grade='" + grade + '\'' +
                '}';
    }

    public Long getAcademicId() {
        return academicId;
    }

    public Date getDate() {
        return date;
    }

    public Integer getMarks() {
        return marks;
    }

    public String getTerm() {
        return term;
    }

    public String getSubject() {
        return subject;
    }

    public Long getUserId() {
        return userId;
    }

    public String getGrade() {
        return grade;
    }
}
