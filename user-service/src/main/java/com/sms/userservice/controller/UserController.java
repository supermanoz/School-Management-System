package com.sms.userservice.controller;

import com.sms.pojo.UserPojo;
import com.sms.response.SmsResponse;
import com.sms.userservice.service.UserService;
import com.sms.model.user_management.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;
    @GetMapping("/fetch")
    public ResponseEntity<?> getUser(@RequestParam("email") String email){
        User user=userService.getByEmail(email);
        UserPojo userRes=new UserPojo(user.getUserId(),user.getFirstName(),user.getLastName(),user.getEmail(),user.getPassword(),user.getRoles().getRoleId());
//        return user;
        return ResponseEntity.ok().body(new SmsResponse<UserPojo>(HttpStatus.OK.value(),HttpStatus.OK.name(),true,userRes));
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody User user){
        user.setPassword(BCrypt.hashpw(user.getPassword(),BCrypt.gensalt()));
        User savedUser=userService.save(user);
        return ResponseEntity.ok().body(new SmsResponse<User>(HttpStatus.OK.value(),HttpStatus.OK.name(),true,savedUser));
    }
}
