package com.sms.academicservice.config;

import com.sms.filter.RequestSourceFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    @Value("${sms.auth.token.header.name}")
    private String AUTH_TOKEN_HEADER_NAME;
    @Value("${sms.auth.token.value}")
    private String AUTH_TOKEN;
    @Bean
    public FilterRegistrationBean<RequestSourceFilter> requestSourceFilterFilterRegistrationBean(){
        FilterRegistrationBean<RequestSourceFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new RequestSourceFilter(AUTH_TOKEN_HEADER_NAME,AUTH_TOKEN));
        registrationBean.addUrlPatterns("/api/courses/*","/api/periods/*","/api/academics/*");
        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean<AuthenticationFilter> authFilterFilterRegistrationBean(){
        FilterRegistrationBean<AuthenticationFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new AuthenticationFilter(AUTH_TOKEN_HEADER_NAME,AUTH_TOKEN));
        registrationBean.addUrlPatterns("/api/courses/*","/api/periods/*","/api/academics/*");
        return registrationBean;
    }
}
