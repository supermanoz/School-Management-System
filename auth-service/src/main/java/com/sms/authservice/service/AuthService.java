package com.sms.authservice.service;

import com.sms.authservice.dto.AuthRequest;
import com.sms.authservice.dto.AuthResponse;
import com.sms.authservice.userdetail.SmsUserDetails;

public interface AuthService {
    public AuthResponse getToken(AuthRequest user) throws Exception;

    public SmsUserDetails getPrincipal();
}
