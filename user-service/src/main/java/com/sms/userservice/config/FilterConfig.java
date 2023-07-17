package com.sms.userservice.config;

import com.sms.filter.AuthenticationFilter;
import com.sms.filter.RequestSourceFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class FilterConfig {

    @Value("${sms.auth.token.header.name}")
    private String AUTH_TOKEN_HEADER_NAME;
    @Value("${sms.auth.token.value}")
    private String AUTH_TOKEN;
    @Autowired
    WebClient.Builder webClientBuilder;
    @Bean
    public FilterRegistrationBean<RequestSourceFilter> requestSourceFilterFilterRegistrationBean(){
        FilterRegistrationBean<RequestSourceFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new RequestSourceFilter(AUTH_TOKEN_HEADER_NAME,AUTH_TOKEN));
        registrationBean.addUrlPatterns("/api/users/*");
        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean<AuthenticationFilter> authenticationFilterFilterRegistrationBean(){
        FilterRegistrationBean<AuthenticationFilter> registrationBean=new FilterRegistrationBean<>();
        registrationBean.setFilter(new AuthenticationFilter(AUTH_TOKEN_HEADER_NAME,AUTH_TOKEN,webClientBuilder));
        registrationBean.addUrlPatterns("/api/users/fetchAllStudents","/api/users/fetchMany","/api/users/fetchStudent/*","/api/users/save");
        return registrationBean;
    }
}
