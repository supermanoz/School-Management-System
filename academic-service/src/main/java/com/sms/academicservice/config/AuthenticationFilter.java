package com.sms.academicservice.config;

import com.sms.response.SmsResponse;
import nonapi.io.github.classgraph.json.JSONUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthenticationFilter extends OncePerRequestFilter {

    private static final Logger LOGGER= LoggerFactory.getLogger(AuthenticationFilter.class);
    private String AUTH_TOKEN_HEADER_NAME;
    private String AUTH_TOKEN;

    public AuthenticationFilter(){}

    public AuthenticationFilter(String AUTH_TOKEN_HEADER_NAME, String AUTH_TOKEN) {
        this.AUTH_TOKEN_HEADER_NAME = AUTH_TOKEN_HEADER_NAME;
        this.AUTH_TOKEN = AUTH_TOKEN;
    }

    @Bean
    @LoadBalanced
    private RestTemplate restTemplate(){
        return new RestTemplate();
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        WebClient.builder().build().get()
//                .uri("http://AUTH-SERVICE/api/auth/authenticate")
//                .headers(header -> {
//                    header.set(AUTH_TOKEN_HEADER_NAME, AUTH_TOKEN);
//                    header.set("Authorization", request.getHeader("Authorization"));
//                })
//                .exchange()
//                .flatMap(respond -> respond.statusCode().value() == 200? System.out.println("hello"):System.out.println("hello"));
//        filterChain.doFilter(request, response);
        HttpHeaders headers=new HttpHeaders();
        headers.set(AUTH_TOKEN_HEADER_NAME, AUTH_TOKEN);
        headers.set("Authorization", request.getHeader("Authorization"));
        HttpEntity requestEntity=new HttpEntity(headers);
        ResponseEntity responseEntity =restTemplate().exchange("http://localhost:8091/api/auth/authenticate", HttpMethod.GET,requestEntity, SmsResponse.class);
        System.out.println(((SmsResponse)responseEntity.getBody()).getMessage());
        if(((SmsResponse)responseEntity.getBody()).getStatus()){
            filterChain.doFilter(request,response);
        }
    }
}
