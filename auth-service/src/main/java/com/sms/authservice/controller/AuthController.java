package com.sms.authservice.controller;

import com.sms.authservice.dto.AuthRequest;
import com.sms.authservice.dto.AuthResponse;
import com.sms.authservice.service.AuthService;
import com.sms.response.SmsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @GetMapping("/test")
    public String test(){
        return "Hello, I'm Auth Service";
    }

    @GetMapping("/getToken")
    public ResponseEntity<SmsResponse> getToken(@RequestBody AuthRequest user) throws Exception {
        return ResponseEntity.ok().body(new SmsResponse("JWT Token",true,authService.getToken(user)));
    }

    @GetMapping("/authenticate")
    public ResponseEntity<SmsResponse> authenticate(){
        return ResponseEntity.ok().body(new SmsResponse("Authenticated",true,true));
    }
}
