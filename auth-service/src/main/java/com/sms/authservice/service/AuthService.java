package com.sms.authservice.service;

import com.sms.authservice.dto.AuthRequest;
import com.sms.authservice.dto.AuthResponse;

public interface AuthService {
    public AuthResponse getToken(AuthRequest user) throws Exception;
}
