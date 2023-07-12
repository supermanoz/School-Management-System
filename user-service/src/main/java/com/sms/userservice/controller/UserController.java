package com.sms.userservice.controller;

import com.sms.exception.NotFoundException;
import com.sms.pojo.user_management.UserPojo;
import com.sms.response.SmsResponse;
import com.sms.userservice.service.UserService;
import com.sms.model.user_management.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;
    @GetMapping("/fetch")
    public ResponseEntity<SmsResponse> getUserByEmail(@RequestParam("email") String email) {
        Map<String, Objects> userRes=userService.getByEmail(email);
        System.out.println(userRes);
        if(userRes.isEmpty()){
            throw new NotFoundException("Unregistered Email!");
        }
        return ResponseEntity.ok().body(new SmsResponse("",true,userRes));
    }

    @GetMapping("/fetch/{id}")
    public ResponseEntity<SmsResponse> getUser(@PathVariable Long id) {
        User user=userService.getById(id);
        UserPojo userRes=userToDto(user);
        return ResponseEntity.ok().body(new SmsResponse("",true,userRes));
    }

    @PostMapping("/fetchMany")
    public ResponseEntity<SmsResponse> getManyUsers(@RequestBody List<Long> ids) {
        List<User> users=userService.getManyById(ids);
        List<UserPojo> userRes=new ArrayList<>();
        users.forEach(user->userRes.add(userToDto(user)));
        return ResponseEntity.ok().body(new SmsResponse("",true,userRes));
    }

    @GetMapping("/fetchAllStudents")
    public ResponseEntity<SmsResponse> getAllStudents() {
        List<User> users=userService.getAllStudents();
        List<UserPojo> userRes=new ArrayList<>();
        users.forEach(user->userRes.add(userToDto(user)));
        return ResponseEntity.ok().body(new SmsResponse("",true,userRes));
    }

    @GetMapping("/fetchStudent/{id}")
    public ResponseEntity<SmsResponse> getStudent(Long id) {
        List<User> users=userService.getAllStudents();
        List<UserPojo> userRes=new ArrayList<>();
        users.forEach(user->userRes.add(userToDto(user)));
        return ResponseEntity.ok().body(new SmsResponse("",true,userRes));
    }

    @PostMapping("/save")
    public ResponseEntity<SmsResponse> save(@RequestBody User user){
        user.setPassword(BCrypt.hashpw(user.getPassword(),BCrypt.gensalt()));
        User savedUser=userService.save(user);
        return ResponseEntity.ok().body(new SmsResponse("",true,savedUser));
    }

    private UserPojo userToDto(User user){
        UserPojo userPojo=new UserPojo(user.getUserId(),user.getFirstName(),user.getLastName(),user.getEmail(),user.getRoles().getRole());
        return userPojo;
    }
}
