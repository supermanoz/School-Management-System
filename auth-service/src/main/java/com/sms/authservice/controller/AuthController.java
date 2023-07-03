package com.sms.authservice.controller;

import com.sms.authservice.dto.AuthRequest;
import com.sms.authservice.dto.AuthResponse;
import com.sms.authservice.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public AuthResponse getToken(@RequestBody AuthRequest user) throws Exception {
        return authService.getToken(user);
    }
}
