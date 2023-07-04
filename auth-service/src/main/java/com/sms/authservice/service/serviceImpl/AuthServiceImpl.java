package com.sms.authservice.service.serviceImpl;

import com.sms.authservice.dto.AuthRequest;
import com.sms.authservice.dto.AuthResponse;
import com.sms.authservice.service.AuthService;
import com.sms.authservice.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailsService userDetailsService;
    @Override
    public AuthResponse getToken(AuthRequest user) throws Exception{
        try{
            System.out.println(user.getEmail()+" "+user.getPassword());
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(),user.getPassword()));
        }catch (BadCredentialsException e){
            throw new Exception("Username or password wrong!");
        }
        UserDetails userDetails=userDetailsService.loadUserByUsername(user.getEmail());
        return new AuthResponse(jwtUtil.generateToken(userDetails));
    }
}
