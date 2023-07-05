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

    /**
     *
     * @param attendanceId
     * @param attendancePojo
     * @return
     */
    @Override
    public AttendancePojo checkInOutAttendance(Long attendanceId, AttendancePojo attendancePojo) {

        //CHECKIN
        if (attendanceId==null){

            Attendance attendance =new Attendance();
            attendance.setAttendanceId(attendance.getAttendanceId());
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

        //CHECKOUT
        Optional<Attendance> attendance = attendanceRepo.findById(attendanceId);

        attendance.get().setCheckOut(LocalDateTime.now());

        attendanceRepo.save(attendance.get());
        AttendancePojo attendancePojo1 = new AttendancePojo();
        attendancePojo1.setAttendanceId(attendance.get().getAttendanceId());
        attendancePojo1.setUserId(attendance.get().getUserId());
        attendancePojo1.setSubjectCode(attendance.get().getSubjectCode());
        attendancePojo1.setCheckOut(attendance.get().getCheckOut());

        return attendancePojo1;

    }

    @Override
    public List<AttendancePojo> getAllAttendance() {

        List<Attendance> listOfAttendance = attendanceRepo.findAll();

        List<AttendancePojo> attendancePojo = listOfAttendance.stream()
                .map(attendance -> {
                    AttendancePojo pojo = new AttendancePojo();
                    pojo.setAttendanceId(attendance.getAttendanceId());
                    pojo.setUserId(attendance.getUserId());
                    pojo.setCheckIn(attendance.getCheckIn());
                    pojo.setCheckOut(attendance.getCheckOut());
                    pojo.setSubjectCode(attendance.getSubjectCode());

                    return pojo;
                }).collect(Collectors.toList());

        return attendancePojo;
    }

    @Override
    public AttendancePojo getByAttendanceId(Long attendanceId) {

        Optional<Attendance> attendance = attendanceRepo.findById(attendanceId);
        if(!attendance.isPresent()){
            throw new NotFoundException("this is userId is not Found in Database");
        }

        AttendancePojo attendancePojo = new AttendancePojo();
        attendancePojo.setAttendanceId(attendance.get().getAttendanceId());
        attendancePojo.setUserId(attendance.get().getUserId());
        attendancePojo.setCheckIn(attendance.get().getCheckIn());
        attendancePojo.setCheckOut(attendance.get().getCheckOut());
        attendancePojo.setSubjectCode(attendance.get().getSubjectCode());

        return attendancePojo;
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


