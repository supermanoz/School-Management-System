package com.sms.authservice.userdetail;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sms.pojo.user_management.UserDetailsPojo;
import com.sms.response.SmsResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class SmsUserDetailsService implements UserDetailsService {

    private static final Logger LOGGER= LoggerFactory.getLogger(SmsUserDetailsService.class);
    @Autowired
    private WebClient.Builder webClient;

    @Value("${sms.auth.token.header.name}")
    private String AUTH_TOKEN_HEADER_NAME;

    @Value("${sms.auth.token.value}")
    private String AUTH_TOKEN;

    private final String baseUrl = "http://USER-SERVICE";
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SmsResponse response=webClient.baseUrl(baseUrl)
                .build()
                .get()
                .uri("/api/users/fetch?email="+username)
                .header(AUTH_TOKEN_HEADER_NAME,AUTH_TOKEN)
                .retrieve()
                .bodyToMono(SmsResponse.class)
                .block();
        LOGGER.trace("The status of response is: "+response.getStatus());
        if(!response.getStatus()){
            throw new UsernameNotFoundException(response.getMessage());
        }
        ObjectMapper objectMapper=new ObjectMapper();
        UserDetailsPojo user=objectMapper.convertValue(response.getPayload(),UserDetailsPojo.class);
        return new SmsUserDetails(user);
    }
}
