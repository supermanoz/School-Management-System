package com.sms.academicservice.service.serviceImpl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sms.academicservice.dto.AcademicRequest;
import com.sms.academicservice.pojo.SubjectResult;
import com.sms.academicservice.pojo.TermResult;
import com.sms.academicservice.repository.CourseRepository;
import com.sms.academicservice.repository.StudentGradeHistoryRepository;
import com.sms.academicservice.service.AcademicService;
import com.sms.academicservice.repository.AcademicRepository;
import com.sms.enums.academic_management.GradeEnum;
import com.sms.enums.academic_management.TermEnum;
import com.sms.enums.user_management.UserEnum;
import com.sms.exception.AlreadyExistsException;
import com.sms.exception.NotFoundException;
import com.sms.model.academic_management.Academic;
import com.sms.model.academic_management.Course;
import com.sms.model.academic_management.StudentGradeHistory;
import com.sms.pojo.academic_management.AcademicDto;
import com.sms.pojo.academic_management.AcademicPojo;
import com.sms.pojo.user_management.StudentPojo;
import com.sms.pojo.user_management.UserPojo;
import com.sms.response.SmsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import javax.management.InvalidAttributeValueException;
import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class AcademicServiceImpl implements AcademicService {

    @Autowired
    private WebClient.Builder webClientBuilder;
    @Autowired
    private AcademicRepository academicRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private StudentGradeHistoryRepository studentGradeHistoryRepository;
    @Autowired
    private HttpServletRequest request;
    @Override
    public TermResult getByUserIdAndTerm(Long userId, TermEnum term) {
        List<Map<String,Object>> academicList= academicRepository.findByUserId(userId,term.name());
        SmsResponse res=webClientBuilder.build().get()
                .uri("http://USER-SERVICE/api/users/fetch/"+userId)
                .headers(headers -> {
                    headers.set("SMS_API_KEY","thisisasecret");
                    headers.set("Authorization",request.getHeader("Authorization"));
                })
                .retrieve()
                .bodyToMono(SmsResponse.class)
                .block();
        if(!res.getStatus()){
            throw new NotFoundException("User with the given ID not found!");
        }
        ObjectMapper mapper=new ObjectMapper();
        UserPojo userRes=mapper.convertValue(res.getPayload(),UserPojo.class);

        StudentPojo student=StudentPojo.builder()
                .userId(userRes.getUserId())
                .firstName(userRes.getFirstName())
                .lastName(userRes.getLastName())
                .grade(getGrade(userRes.getUserId()))
                .email(userRes.getEmail())
                .build();

        TermResult termResult=new TermResult();
        termResult.setTerm(term);
        termResult.setStudent(student);
        AtomicInteger totalMarks= new AtomicInteger();
        List<SubjectResult> results=new ArrayList<>();
        academicList.forEach(academic->{
            SubjectResult subjectResult=new SubjectResult(((BigInteger)academic.get("academicId")).longValue(),(String)academic.get("subject"),(Integer)academic.get("marks"));
            results.add(subjectResult);
            totalMarks.set(totalMarks.get() + (Integer) academic.get("marks"));
        });
        termResult.setAcademics(results);
        termResult.setPercentage(totalMarks.get() *0.125); //assuming 8 subjects to calculate percentage

        return termResult;
    }

//    @Override
//    public List<AcademicPojo> getByGrade(GradeEnum grade) {
//        String gradeStr=grade.toString();
//        List<Map<String,Object>> academicList= academicRepository.getByGrade(gradeStr);
//        Long userIds[]=new Long[academicList.size()];
//        AtomicReference<Integer> userIdIndex= new AtomicReference<>(0);
//        academicList.forEach(academics->{
//            userIds[userIdIndex.get()]=((BigInteger)academics.get("userId")).longValue();
//            userIdIndex.getAndSet(userIdIndex.get() + 1);
//        });
//        SmsResponse res=webClientBuilder.build().post()
//                .uri("http://USER-SERVICE/api/users/fetchMany")
//                .headers(headers -> {
//                    headers.set("SMS_API_KEY","thisisasecret");
//                    headers.set("Authorization",request.getHeader("Authorization"));
//                })
//                .contentType(MediaType.APPLICATION_JSON)
//                .bodyValue(userIds)
//                .retrieve()
//                .bodyToMono(SmsResponse.class)
//                .block();
//        if(!res.getStatus()){
//            throw new NotFoundException("User with the given ID not found!");
//        }
//        ObjectMapper mapper=new ObjectMapper();
//
//        List<Map<String,Object>> userListRes=mapper.convertValue(res.getPayload(),List.class);
//        userListRes.forEach(user-> System.out.println(user.get("email")+" "+user.get("userId")));
//
//        List<AcademicPojo> academicRes=new ArrayList<>();
//        academicList.forEach(academic->{
//            academicRes.add(AcademicPojo.builder()
//                    .academicId(((BigInteger)academic.get("academicId")).longValue())
//                    .subject((String)academic.get("subject"))
//                    .marks((Integer)academic.get("marks"))
//                    .date((Date)academic.get("date"))
//                    .term(TermEnum.valueOf((String)academic.get("term")))
//                    .grade(GradeEnum.valueOf((String)academic.get("grade")))
//                    .user(getByUserId(((BigInteger)academic.get("userId")).longValue(),userListRes))
//                    .build()
//            );
//        });
//        return academicRes;
//    }

    @Override
    public TermResult getByUserIdAndTermAndGrade(Long userId,TermEnum term,GradeEnum grade) {
        List<AcademicDto> academicDtos=academicRepository.getByUserIdAndTermAndGrade(userId, term.name(), grade.name());
        SmsResponse res=webClientBuilder.build().get()
                .uri("http://USER-SERVICE/api/users/fetch/"+userId)
                .headers(headers -> {
                    headers.set("SMS_API_KEY","thisisasecret");
                    headers.set("Authorization",request.getHeader("Authorization"));
                })
                .retrieve()
                .bodyToMono(SmsResponse.class)
                .block();
        if(!res.getStatus()){
            throw new NotFoundException("User with the given ID not found!");
        }
        ObjectMapper mapper=new ObjectMapper();
        UserPojo userRes=mapper.convertValue(res.getPayload(),UserPojo.class);

        StudentPojo student=StudentPojo.builder()
                .userId(userRes.getUserId())
                .firstName(userRes.getFirstName())
                .lastName(userRes.getLastName())
                .grade(getGrade(userRes.getUserId()))
                .email(userRes.getEmail())
                .build();

        TermResult termResult=new TermResult();
        termResult.setTerm(term);
        termResult.setStudent(student);
        AtomicInteger totalMarks= new AtomicInteger();
        List<SubjectResult> results=new ArrayList<>();
        academicDtos.forEach(academic->{
            SubjectResult subjectResult=new SubjectResult(academic.getAcademicId(),academic.getSubject(),academic.getMarks());
            results.add(subjectResult);
            totalMarks.set(totalMarks.get() + academic.getMarks());
        });
        termResult.setAcademics(results);
        termResult.setPercentage(totalMarks.get() *0.125); //assuming 8 subjects to calculate percentage

        return termResult;
    }

    @Override
    public AcademicPojo save(AcademicRequest academicRequest) throws InvalidAttributeValueException {
        SmsResponse res=webClientBuilder.build().get()
                .uri("http://USER-SERVICE/api/users/fetch/"+academicRequest.getUserId())
                .headers(headers -> {
                    headers.set("SMS_API_KEY","thisisasecret");
                    headers.set("Authorization",request.getHeader("Authorization"));
                })
                .retrieve()
                .bodyToMono(SmsResponse.class)
                .block();
        if(!res.getStatus()){
            throw new NotFoundException("User with the given ID not found!");
        }
        ObjectMapper mapper=new ObjectMapper();
        UserPojo userRes=mapper.convertValue(res.getPayload(),UserPojo.class);

        Optional<Course> courseOptional=courseRepository.findById(academicRequest.getCourseId());
        if(academicRepository.findByUserIdAndCourse(academicRequest.getUserId(),courseOptional.get()).isPresent()){
            throw new AlreadyExistsException("Duplicate academic entry!");
        }
        if(!userRes.getRole().equals(UserEnum.STUDENT)){
            throw new InvalidAttributeValueException("User is not a student!");
        }
        if(!courseOptional.isPresent() || !courseOptional.get().getGrade().equals(getGrade(academicRequest.getUserId()))){
            throw new InvalidAttributeValueException("Invalid course!");
        }
        Academic academic=Academic.builder()
                .academicId(academicRequest.getAcademicId())
                .course(Course.builder().courseId(academicRequest.getCourseId()).build())
                .userId(academicRequest.getUserId())
                .term(academicRequest.getTerm())
                .marks(academicRequest.getMarks())
                .date(new Date())
                .build();
        Academic savedAcademic=academicRepository.save(academic);

        return AcademicPojo.builder()
                .academicId(savedAcademic.getAcademicId())
                .user(userRes)
                .term(savedAcademic.getTerm())
                .subject(savedAcademic.getCourse().getSubject())
                .marks(savedAcademic.getMarks())
                .date(savedAcademic.getDate())
                .grade(savedAcademic.getCourse().getGrade())
                .build();
    }

    @Override
    public GradeEnum getGrade(Long userId) {
        Optional<StudentGradeHistory> gradeHistory=studentGradeHistoryRepository.findByStudnetId(userId);
        if(!gradeHistory.isPresent()){
            throw new NotFoundException("Grade of student not found!");
        }
        return gradeHistory.get().getGrade();
    }

    private UserPojo getByUserId(Long userId,List<Map<String,Object>> users){
        for(Map<String,Object> user:users){
            if(user.get("userId")==userId)
                return UserPojo.builder()
                        .userId(((Integer)user.get("userId")).longValue())
                        .firstName((String)user.get("firstName"))
                        .lastName((String)user.get("lastName"))
                        .email((String)user.get("email"))
                        .role(UserEnum.valueOf((String)user.get("role")))
                        .build();
        }
        UserPojo userPojo=new UserPojo();
        return userPojo;
    }
}