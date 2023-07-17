package com.sms.filter;

import com.sms.response.SmsResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.support.SecurityContextProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthenticationFilter extends OncePerRequestFilter {

    private static final Logger LOGGER= LoggerFactory.getLogger(AuthenticationFilter.class);
    private String AUTH_TOKEN_HEADER_NAME;
    private String AUTH_TOKEN;

//    @Bean
//    private WebClient.Builder webClientBuilder(){
//        return WebClient.builder();
//    }

    private WebClient.Builder webClientBuilder;

    public AuthenticationFilter(){}

    public AuthenticationFilter(String AUTH_TOKEN_HEADER_NAME, String AUTH_TOKEN,WebClient.Builder webClientBuilder) {
        this.AUTH_TOKEN_HEADER_NAME = AUTH_TOKEN_HEADER_NAME;
        this.AUTH_TOKEN = AUTH_TOKEN;
        this.webClientBuilder=webClientBuilder;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//                WebClient.builder().build().get()
//                .uri("http://AUTH-SERVICE/api/auth/authenticate")
//                .headers(header -> {
//                    header.set(AUTH_TOKEN_HEADER_NAME, AUTH_TOKEN);
//                    header.set("Authorization", request.getHeader("Authorization"));
//                })
//                        .retrieve()
//                        .bodyToMono(SmsResponse.class)
//                        .map(smsres->smsres.getMessage());

//        HttpHeaders headers=new HttpHeaders();
//        headers.set(AUTH_TOKEN_HEADER_NAME, AUTH_TOKEN);
//        headers.set("Authorization", request.getHeader("Authorization"));
//        HttpEntity requestEntity=new HttpEntity(headers);
//        ResponseEntity responseEntity =restTemplate().exchange("http://localhost:8091/api/auth/authenticate", HttpMethod.GET,requestEntity, SmsResponse.class);
//        SmsResponse res=(SmsResponse)responseEntity.getBody();
//        if(res.getStatus()){
//            System.out.println("This is the status from authentication: "+res.getMessage());
//            filterChain.doFilter(request,response);
//        }


        try{
            SmsResponse res=webClientBuilder.build().get()
                    .uri("http://AUTH-SERVICE/api/auth/authenticate")
                    .headers(header -> {
                        header.set(AUTH_TOKEN_HEADER_NAME, AUTH_TOKEN);
                        header.set("Authorization", request.getHeader("Authorization"));
                    })
                    .retrieve()
                    .bodyToMono(SmsResponse.class)
                    .block();
            if(res.getStatus()){
                System.out.println("This is the status from authentication: "+res.getMessage());
                filterChain.doFilter(request,response);
            }else{
            }
        }catch (WebClientResponseException.BadRequest webClientResponseException){
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.setStatus(401);
            response.getWriter().write((new SmsResponse("Invalid Auth-Token",false,null)).toString());
        }

    }
}
