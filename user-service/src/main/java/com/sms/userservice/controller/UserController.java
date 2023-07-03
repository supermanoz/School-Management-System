package com.sms.userservice.controller;

import com.sms.exception.NotFoundException;
import com.sms.pojo.UserPojo;
import com.sms.response.SmsResponse;
import com.sms.model.user_management.User;
import com.sms.services.user_management.UserService;
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
        User user = userService.getByEmail(email);
        if(user==null){
            throw new NotFoundException("Unregistered Email!");
        }
        UserPojo userRes = userToDto(user);
        return ResponseEntity.ok().body(new SmsResponse("",true, new SmsResponse("",true, userRes)));
    }

    @GetMapping("/fetch/{id}")
    public ResponseEntity<SmsResponse> getUser(@PathVariable Long id) {
        User user=userService.getById(id);
        if(user==null){
            throw new NotFoundException("User Not Found!");
        }
        UserPojo userRes=userToDto(user);
        return ResponseEntity.ok().body(new SmsResponse("",true,new SmsResponse("",true, user)));
    }

    @PostMapping("/fetchMany")
    public ResponseEntity<SmsResponse> getManyUsers(@RequestBody List<Long> ids) {
        List<User> users=userService.getManyById(ids);
        List<UserPojo> userRes=new ArrayList<>();
        users.forEach(user->userRes.add(userToDto(user)));
        return ResponseEntity.ok().body(new SmsResponse("",true,new SmsResponse("",true, userRes)));
    }

    @GetMapping("/fetchAllStudents")
    public ResponseEntity<SmsResponse> getAllStudents() {
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
        UserPojo userPojo=new UserPojo(user.getUserId(),user.getFirstName(),user.getLastName(),user.getEmail(),user.getPassword(),user.getRoles().getRoleId());
        return userPojo;
    }
}
