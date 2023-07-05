package com.sms.attendanceservice.serviceImpl;

import com.sms.attendanceservice.model.Attendance;
import com.sms.attendanceservice.pojo.AttendancePojo;
import com.sms.attendanceservice.repository.AttendanceRepository;
import com.sms.attendanceservice.service.AttendanceService;

import com.sms.exception.NotFoundException;
import com.sms.repository.user_management.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AttendanceServiceImpl implements AttendanceService {

    @Autowired
    private AttendanceRepository attendanceRepo;
    @Autowired
    private UserRepository userRepository;

    @Override
    public AttendancePojo checkInAttendance(AttendancePojo attendancePojo) {

        Attendance attendance = new Attendance();

        attendance.setAttendanceId(attendancePojo.getAttendanceId());
        attendance.setUserId(attendancePojo.getUserId());
        attendance.setSubjectCode(attendancePojo.getSubjectCode());
        attendance.setCheckIn(LocalDateTime.now());

        attendanceRepo.save(attendance);

        AttendancePojo attendancePojo1 = new AttendancePojo();
        attendancePojo1.setAttendanceId(attendance.getAttendanceId());
        attendancePojo1.setUserId(attendance.getUserId());
        attendancePojo1.setCheckIn(attendance.getCheckIn());
        attendancePojo1.setSubjectCode(attendance.getSubjectCode());

        return attendancePojo1;
    }

    @Override
    public AttendancePojo chekOutAttendance(Long attendanceId, AttendancePojo attendancePojo) {

          Optional<Attendance> updateAttendance = attendanceRepo.findById(attendanceId);

          if (!updateAttendance.isPresent()){
            throw new NotFoundException("attendance is not exist");
        }
        updateAttendance.get().setCheckOut(LocalDateTime.now());
          attendanceRepo.save(updateAttendance.get());

          AttendancePojo attendancePojo1 = new AttendancePojo();
            attendancePojo1.setAttendanceId(updateAttendance.get().getAttendanceId());
            attendancePojo1.setUserId(updateAttendance.get().getUserId());
            attendancePojo1.setSubjectCode(updateAttendance.get().getSubjectCode());
            attendancePojo1.setCheckOut(updateAttendance.get().getCheckOut());

          return attendancePojo1;


    }


    @Override
    public List<AttendancePojo> getAllAttendance() {

        List<Attendance> attendances = attendanceRepo.findAll();

        if(attendances.isEmpty())
        {
            throw new NotFoundException("attendance database is empty");
        }

        List<AttendancePojo> attendancePojoList =  attendances.stream()
                .map(attendance -> {
                    AttendancePojo pojo = new AttendancePojo();
                    pojo.setAttendanceId(attendance.getAttendanceId());
                    pojo.setUserId(attendance.getUserId());
                    pojo.setSubjectCode(attendance.getSubjectCode());
                    pojo.setCheckIn(attendance.getCheckIn());
                    pojo.setCheckOut(attendance.getCheckOut());

                    return pojo;

        }).collect(Collectors.toList());


        return attendancePojoList;
    }

    @Override
    public List<Attendance> getAttendanceByUserId(Long userId) { return null; }

    @Override
    public List<Attendance> getAllAttendanceBetweenDates(String from, String to) {
        return null;
    }

    @Override
    public List<Attendance> getAllAttendanceBetweenDatesByUserId(Long userId, String from, String to) {
        return null;
    }
}


