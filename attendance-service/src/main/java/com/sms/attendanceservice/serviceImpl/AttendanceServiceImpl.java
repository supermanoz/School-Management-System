package com.sms.attendanceservice.serviceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sms.attendanceservice.model.Attendance;
import com.sms.attendanceservice.pojo.AttendancePojo;
import com.sms.attendanceservice.pojo.DateTimePojo;
import com.sms.attendanceservice.repository.AttendanceRepository;
import com.sms.attendanceservice.service.AttendanceService;
import com.sms.exception.NotFoundException;
import com.sms.model.user_management.Course;
import com.sms.model.user_management.Role;
import com.sms.model.user_management.User;
import com.sms.pojo.CoursePojo;
import com.sms.pojo.UserPojo;
import com.sms.repository.user_management.UserRepository;
import com.sms.response.SmsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AttendanceServiceImpl implements AttendanceService {

    @Value("${sms.auth.token.header.name}")
    private String headerName;

    @Value("${sms.auth.token.value}")
    private String headerValue;

    @Autowired
    private AttendanceRepository attendanceRepo;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WebClient.Builder webClientBuilder;

  /*  @Override
    public AttendancePojo checkInOutAttendance( Long userId, HttpServletRequest request) {

        SmsResponse res = null;
        try {
            res = webClientBuilder.build()
                    .get()
                    .uri("http://USER-SERVICE/api/users/fetch/" + userId)
                    .headers(header -> {
                        header.set(headerName, headerValue);
                        header.set("Authorization", request.getHeader("Authorization"));
                    })
                    .retrieve()
                    .bodyToMono(SmsResponse.class)
                    .block();

            ObjectMapper mapper = new ObjectMapper();
            UserPojo userPojo = mapper.convertValue(res.getPayload(), UserPojo.class);
            Role role = new Role();
            role.setRoleId(userPojo.getRoleId());
            User user = new User(userPojo.getUserId(), userPojo.getFirstName(), userPojo.getLastName(), userPojo.getEmail(), userPojo.getPassword(), role);

            Optional<Attendance> attendanceOptional = attendanceRepo.getAttendanceByCheckInAndUserId(userId);

            if (!attendanceOptional.isPresent()) {
                Attendance attendance = Attendance.builder()
                        .checkIn(LocalDateTime.now())
                        .userId(userId)
                        .build();
                attendanceRepo.save(attendance);
                AttendancePojo pojo = new AttendancePojo();
                pojo.setAttendanceId(attendance.getAttendanceId());
                pojo.setUserId(attendance.getUserId());
                pojo.setCheckIn(attendance.getCheckIn());
                pojo.setCourseId(attendance.getCourseId());
                return pojo;
            } else {
                Attendance attendanceCheckOut = attendanceOptional.get();
                attendanceCheckOut.setUserId(userId);
                attendanceCheckOut.setCheckOut(LocalDateTime.now());
                attendanceRepo.save(attendanceCheckOut);

                AttendancePojo pojo = new AttendancePojo();
                pojo.setAttendanceId(attendanceCheckOut.getAttendanceId());
                pojo.setUserId(attendanceCheckOut.getUserId());
                pojo.setCheckOut(attendanceCheckOut.getCheckOut());
                pojo.setCourseId(attendanceCheckOut.getCourseId());
                return pojo;
            }
        } catch (WebClientResponseException exception) {
            throw new NotFoundException("user id not found");
        }
    }*/

    @Override
    public AttendancePojo checkInOutAttendance( Long userId, HttpServletRequest request) {

        SmsResponse res = null;
        try {
            res = webClientBuilder.build()
                    .get()
                    .uri("http://USER-SERVICE/api/users/fetch/" + userId)
                    .headers(header -> {
                        header.set(headerName, headerValue);
                        header.set("Authorization", request.getHeader("Authorization"));
                    })
                    .retrieve()
                    .bodyToMono(SmsResponse.class)
                    .block();

            ObjectMapper mapper = new ObjectMapper();
            UserPojo userPojo = mapper.convertValue(res.getPayload(), UserPojo.class);
//            Role role = new Role();
//            role.setRoleId(userPojo.getUserId());
            User user = new User(userPojo.getUserId(), userPojo.getFirstName(), userPojo.getLastName(), userPojo.getEmail(),userPojo.getRole());

            Optional<Attendance> attendanceOptional = attendanceRepo.getAttendanceByCheckInAndUserId(userId);

            if (!attendanceOptional.isPresent()) {
                Attendance attendance = Attendance.builder()
                        .checkIn(LocalDateTime.now())
                        .userId(userId)
                        .build();
                attendanceRepo.save(attendance);
                AttendancePojo pojo = new AttendancePojo();
                pojo.setAttendanceId(attendance.getAttendanceId());
                pojo.setUserId(attendance.getUserId());
                pojo.setCheckIn(attendance.getCheckIn());

                SmsResponse courseRes = webClientBuilder.build()
                        .get()
                        .uri("http://localhost:8093/api/periods/fetchByCurrentTime")
                        .retrieve()
                        .bodyToMono(SmsResponse.class)
                        .block();

                ObjectMapper courseMapper = new ObjectMapper();
               CoursePojo coursePojo = courseMapper.convertValue(courseRes.getPayload(), CoursePojo.class);

               System.out.println(coursePojo.toString()+"------------->");

                pojo.setPeriod(attendance.getPeriod());
                return pojo;
            } else {
                Attendance attendanceCheckOut = attendanceOptional.get();
                attendanceCheckOut.setUserId(userId);
                attendanceCheckOut.setCheckOut(LocalDateTime.now());
                attendanceCheckOut.setPeriod(attendanceCheckOut.getPeriod());
                attendanceRepo.save(attendanceCheckOut);

                AttendancePojo pojo = new AttendancePojo();
                pojo.setAttendanceId(attendanceCheckOut.getAttendanceId());
                pojo.setUserId(attendanceCheckOut.getUserId());
                pojo.setCheckOut(attendanceCheckOut.getCheckOut());
                pojo.setPeriod(attendanceCheckOut.getPeriod());
                return pojo;
            }
        } catch (WebClientResponseException exception) {
            throw new NotFoundException("user id not found");
        }
    }

    @Override
    public List<AttendancePojo> getAttendanceByUserId(Long userId) {
        List<Attendance> attendanceListByUserId = attendanceRepo.findAttendanceByUserId(userId);

        List<AttendancePojo> attendancePojo = attendanceListByUserId.stream()
                .map(attendance -> {
                    AttendancePojo pojo = new AttendancePojo();
                    pojo.setAttendanceId(attendance.getAttendanceId());
                    pojo.setUserId(attendance.getUserId());
                    pojo.setCheckIn(attendance.getCheckIn());
                    pojo.setCheckOut(attendance.getCheckOut());
                    pojo.setPeriod(attendance.getPeriod());
                    return pojo;
                }).collect(Collectors.toList());

        if (attendancePojo.isEmpty()) {
            throw new NotFoundException("this user not attended yet");
        }
        return attendancePojo;
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

                    return pojo;
                }).collect(Collectors.toList());

        return attendancePojo;
    }


    @Override
    public AttendancePojo getByAttendanceId(Long attendanceId) {

        System.out.println("getByAttendanceId running------------>");
        Optional<Attendance> attendance = attendanceRepo.findById(attendanceId);
        if (!attendance.isPresent()) {
            throw new NotFoundException("this is userId is not Found in Database");
        }
        AttendancePojo attendancePojo = new AttendancePojo();
        attendancePojo.setAttendanceId(attendance.get().getAttendanceId());
//        attendancePojo.setUserId(attendance.get().getUserId());
        attendancePojo.setCheckIn(attendance.get().getCheckIn());
        attendancePojo.setCheckOut(attendance.get().getCheckOut());
//        attendancePojo.setSubjectCode(attendance.get().getSubjectCode());

        return attendancePojo;
    }


    @Override
    public List<AttendancePojo> getAllAttendanceBetweenDates(DateTimePojo dateTimePojo) {


                if (dateTimePojo.getFrom() == null && dateTimePojo.getTo() == null) {
                    throw new NotFoundException("date field cannot be empty");
                }


                List<Attendance> attendancesList = attendanceRepo.getAllAttendanceBetweenDates(dateTimePojo.getFrom(), dateTimePojo.getTo()).get();
                if (attendancesList.isEmpty()) {
                    throw new NotFoundException("there is no attendance between given dates");
                }
                List<AttendancePojo> attendancePojos = attendancesList.stream().map(
                        attendances -> {
                            AttendancePojo pojo = new AttendancePojo();
                            pojo.setAttendanceId(attendances.getAttendanceId());
                            pojo.setUserId(attendances.getUserId());
                            pojo.setCheckIn(attendances.getCheckIn());
                            pojo.setCheckOut(attendances.getCheckOut());
                            return pojo;
                        }).collect(Collectors.toList());
                return attendancePojos;
    }

    @Override
    public List<AttendancePojo> getAllAttendanceBetweenDatesByUserId(Long userId, DateTimePojo dateTimePojo) {

        if (dateTimePojo.getFrom() == null && dateTimePojo.getTo() == null && userId == null) {
            throw new NotFoundException("date field and userId field cannot be empty");
        }

        List<Attendance> userAttendanceList = attendanceRepo.getAllAttendanceBetweenDatesByUserId(userId, dateTimePojo.getFrom(),dateTimePojo.getTo()).get();

        if (userAttendanceList.isEmpty()) {
            throw new NotFoundException("there is no attendance between given dates");
        }
        List<AttendancePojo> attendancePojos = userAttendanceList.stream().map(
                attendances -> {
                    AttendancePojo pojo = new AttendancePojo();
                    pojo.setAttendanceId(attendances.getAttendanceId());
                    pojo.setUserId(attendances.getUserId());
                    pojo.setCheckIn(attendances.getCheckIn());
                    pojo.setCheckOut(attendances.getCheckOut());
                    return pojo;
                }).collect(Collectors.toList());

        return attendancePojos;
    }
}