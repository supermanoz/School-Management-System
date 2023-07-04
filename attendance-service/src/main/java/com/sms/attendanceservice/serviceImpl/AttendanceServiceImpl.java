package com.sms.attendanceservice.serviceImpl;

import com.sms.attendanceservice.model.Attendance;
import com.sms.attendanceservice.pojo.AttendancePojo;
import com.sms.attendanceservice.repository.AttendanceRepository;
import com.sms.attendanceservice.service.AttendanceService;
import com.sms.exception.AlreadyExistException;
import com.sms.exception.NotFoundException;
import com.sms.repository.user_management.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.sql.SQLOutput;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AttendanceServiceImpl implements AttendanceService {

    @Autowired
    private AttendanceRepository attendanceRepo;
    @Autowired
    private UserRepository userRepository;
/*    @Autowired
    private WebClient.Builder webclient;*/


//    @Override
//    public AttendancePojo checkInAttendance(AttendancePojo attendancePojo){
//
////      Optional<Attendance> attendance = attendanceRepo.getByCheckIn(LocalDateTime.now());
//        Optional<Attendance> checkInAttendance = attendanceRepo.getByCheckInAndCreatedBy(attendance.getCreatedBy());
//        System.out.println(checkInAttendance+"----------------------");
//        if(!checkInAttendance.isEmpty()){
//            throw new AlreadyExistException("attendance already exists");
//        }
//
//        System.out.println(" after exception++++++++++++++++");
//        checkInAttendance.get().setCheckIn(LocalDateTime.now());
//        System.out.println(checkInAttendance+"getting------------------");
//        checkInAttendance.get().setCreatedBy(attendance.getCreatedBy());
//        checkInAttendance.get().setUsers(attendance.getUsers());
//
//
//        return attendanceRepo.save(checkInAttendance.get());
//    }

    @Override
    public AttendancePojo checkInAttendance(AttendancePojo attendancePojo) {
        return null;
    }

    @Override
    public Attendance chekOutAttendance(Long attendanceId, Attendance attendance) {

          Optional<Attendance> updatedAttendance = attendanceRepo.findById(attendanceId);
        if (!updatedAttendance.isPresent()){
            throw new NotFoundException("attendance is not exist");
        }

        updatedAttendance.get().setCheckOut(LocalDateTime.now());
        updatedAttendance.get().setUpdatedBy(attendance.getUpdatedBy());
           return attendanceRepo.save(updatedAttendance.get());


    }


    @Override
    public List<Attendance> getAllAttendance() {

        List<Attendance> attendances = attendanceRepo.findAll();
        if(attendances.isEmpty())
        {
            throw new NotFoundException("attendance database is empty");
        }

        return attendances;
    }

    @Override
    public List<Attendance> getAttendanceByUserId(Long userId) {


        return null;
    }

    @Override
    public List<Attendance> getAllAttendanceBetweenDates(String from, String to) {
        return null;
    }

    @Override
    public List<Attendance> getAllAttendanceBetweenDatesByUserId(Long userId, String from, String to) {
        return null;
    }
}


/*

    @Value("${sms.auth.token.header.name}")
    String API_HEADER;

    @Value("${sms.auth.token.value}")
    String API_HEADER_VALUE;

    @Override
    public Attendance checkInAttendance(AttendancePojo attendancePojo) {
        Attendance attendance;
        if(attendancePojo.getUsers()!=null){
            attendance = attendanceRepo.findById(attendancePojo.getAttendanceId()).get();
        }
        else{
            attendance = new Attendance();
        }
        attendance.setCheckIn(LocalDateTime.now());
        List<User> userList = new ArrayList<>();
        System.out.println("--------this is a user Id : "+ attendancePojo.getUsers().get(0));
        attendancePojo.getUsers().forEach(userId->{
            System.out.println("The user id is: "+userId);
            userList.add(userRepository.findById(userId.getUserId()).get());
        } );
        attendance.setUsers(userList);
        Attendance attendanceTaken = attendanceRepo.save(attendance);

        return attendanceTaken;
    }
*/

