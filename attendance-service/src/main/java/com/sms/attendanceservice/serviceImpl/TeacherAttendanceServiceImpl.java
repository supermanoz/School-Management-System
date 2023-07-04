package com.sms.attendanceservice.serviceImpl;

import com.sms.attendanceservice.model.TeacherAttendance;
import com.sms.attendanceservice.pojo.TeacherAttendancePojo;
import com.sms.attendanceservice.repository.TeacherAttendanceRepository;
import com.sms.attendanceservice.service.AttendanceService;
import com.sms.attendanceservice.service.TeacherAttendanceService;
import com.sms.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class TeacherAttendanceServiceImpl implements TeacherAttendanceService {

    @Autowired
    private TeacherAttendanceRepository teacherAttendanceRepo;
    @Override
    public TeacherAttendancePojo createAttendance(TeacherAttendancePojo teacherAttendancePojo) {

        TeacherAttendance teacherAttendance = new TeacherAttendance();

        teacherAttendance.setTeacherId(teacherAttendancePojo.getTeacherId());
        teacherAttendance.setCheckIn(LocalDateTime.now());

        TeacherAttendance teacherAttendance1 = teacherAttendanceRepo.save(teacherAttendance);

        teacherAttendancePojo.setTeacherId(teacherAttendance.getTeacherId());
        teacherAttendancePojo.setCheckIn(teacherAttendance.getCheckIn());

        return teacherAttendancePojo;
    }

    @Override
    public TeacherAttendancePojo updateAttendance(Long TeacherAttendanceId, TeacherAttendancePojo teacherAttendancePojo) {

        Optional<TeacherAttendance> teacherAttendance = teacherAttendanceRepo.findById(TeacherAttendanceId);
        if(!teacherAttendance.isPresent()){
            throw new NotFoundException("Attendance Id Not Present in Database");
        }
        teacherAttendance.get().setCheckOut(LocalDateTime.now());
        teacherAttendance.ifPresent(teacherAttendanceRepo::save);

        TeacherAttendancePojo teacherAttendancePojo1 = new TeacherAttendancePojo();
        teacherAttendancePojo1.setTeacherAttendanceId(teacherAttendance.get().getTeacherAttendanceId());
        teacherAttendancePojo1.setTeacherId(teacherAttendance.get().getTeacherId());
        teacherAttendancePojo1.setCheckOut(LocalDateTime.now());


        return teacherAttendancePojo1;
    }


    @Override
    public List<TeacherAttendancePojo> getAllAttendance() {

        List<TeacherAttendance> teacherAttendances = teacherAttendanceRepo.findAll();

        if (teacherAttendances.isEmpty()){
            throw new NotFoundException("Attendance List is Empty");
        }

        List<TeacherAttendancePojo> teacherAttendancePojoList = teacherAttendances.stream()
                .map( teacherAttendance -> {
                    TeacherAttendancePojo pojo = new TeacherAttendancePojo();
                    //set the properties of the pojo based on the
                    pojo.setTeacherAttendanceId(teacherAttendance.getTeacherAttendanceId());
                    pojo.setCheckIn(teacherAttendance.getCheckIn());
                    pojo.setCheckOut(teacherAttendance.getCheckOut());
                    pojo.setTeacherId(teacherAttendance.getTeacherId());

                    return pojo;
                        }

                ).collect(Collectors.toList());
        return teacherAttendancePojoList;
    }

    @Override
    public TeacherAttendancePojo getById(Long teacherAttendanceId) {

        TeacherAttendance teacherAttendance = teacherAttendanceRepo.findById(teacherAttendanceId).get();




        return null;
    }
}
