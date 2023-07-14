package com.sms.authservice.service.serviceImpl;

import com.sms.authservice.dto.AuthRequest;
import com.sms.authservice.dto.AuthResponse;
import com.sms.authservice.service.AuthService;
import com.sms.authservice.userdetail.SmsUserDetails;
import com.sms.authservice.util.JwtUtil;
import com.sms.exception.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    private static Logger LOGGER= LoggerFactory.getLogger(AuthServiceImpl.class);
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailsService userDetailsService;
    @Override
    public AuthResponse getToken(AuthRequest user) throws Exception {
        LOGGER.trace("Entered Email: "+user.getEmail()+", Password: "+user.getPassword());
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(),user.getPassword()));
        }catch(BadCredentialsException e){
            throw new Exception("Email or Password Wrong");
        }
        UserDetails userDetails=userDetailsService.loadUserByUsername(user.getEmail());
        return new AuthResponse(jwtUtil.generateToken(userDetails));
    }

    @Override
    public SmsUserDetails getPrincipal() {
        if(SecurityContextHolder.getContext().getAuthentication().getPrincipal()==null){
            throw new NotFoundException("Principal is null!");
        }
        return (SmsUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
