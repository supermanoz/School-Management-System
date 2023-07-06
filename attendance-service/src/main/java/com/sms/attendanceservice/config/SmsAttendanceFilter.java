package com.sms.attendanceservice.config;

import com.sms.filter.RequestSourceFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SmsAttendanceFilter {

    @Value("${sms.auth.token.header.name}")
    private String AUTH_TOKEN_HEADER_NAME;
    @Value("${sms.auth.token.value}")
    private String AUTH_TOKEN;
    @Bean
    public FilterRegistrationBean<RequestSourceFilter> requestSourceFilterFilterRegistrationBean(){
        FilterRegistrationBean<RequestSourceFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new RequestSourceFilter(AUTH_TOKEN_HEADER_NAME,AUTH_TOKEN));
        registrationBean.addUrlPatterns("/api/attendances/*");
        return registrationBean;
    }
}
