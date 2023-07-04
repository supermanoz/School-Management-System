package com.sms.attendanceservice.serviceImpl;

import com.sms.attendanceservice.model.StudentAttendance;
import com.sms.attendanceservice.pojo.StudentAttendancePojo;
import com.sms.attendanceservice.repository.StudentAttendanceRepository;
import com.sms.attendanceservice.service.StudentAttendanceService;
import com.sms.exception.AlreadyExistException;
import com.sms.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class StudentAttendanceServiceImpl implements StudentAttendanceService {

    @Autowired
    private StudentAttendanceRepository studentAttendanceRepo;

    @Override
    public StudentAttendancePojo createStudentAttendance(StudentAttendancePojo studentAttendancePojo) {

        Optional<StudentAttendance> OptionalStudentAttendance = studentAttendanceRepo.findByAttendDateAndStudentId(LocalDate.now(), studentAttendancePojo.getStudentId());
        if(OptionalStudentAttendance.isPresent()){
             throw new AlreadyExistException("attendance already taken");
        }

        StudentAttendance studentAttendance = new StudentAttendance();
        studentAttendance.setAttendDate(LocalDate.now());
        studentAttendance.setStudentId(studentAttendancePojo.getStudentId());

         studentAttendanceRepo.save(studentAttendance);

         studentAttendancePojo.setAttendDate(studentAttendance.getAttendDate());
         studentAttendancePojo.setStudentAttendanceId(studentAttendance.getStudentAttendanceId());

         return studentAttendancePojo;

            }

    @Override
    public List<StudentAttendance> getAllStudentAttendance() {


        List<StudentAttendance> getAllStudentAttendance = studentAttendanceRepo.findAll();
        if(getAllStudentAttendance.isEmpty()){
            throw new NotFoundException("there is no attendance");
        }

        return getAllStudentAttendance;
    }
}
