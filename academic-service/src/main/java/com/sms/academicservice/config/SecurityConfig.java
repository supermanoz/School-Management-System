package com.sms.academicservice.config;

import com.sms.academicservice.filter.TestAuthFilter;
import com.sms.enums.user_management.UserEnum;
import com.sms.filter.RequestSourceFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class SecurityConfig {
    @Value("${sms.auth.token.header.name}")
    private String AUTH_TOKEN_HEADER_NAME;
    @Value("${sms.auth.token.value}")
    private String AUTH_TOKEN;
    @Autowired
    private WebClient.Builder webClientBuilder;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .cors().and().csrf().disable()
                .authorizeRequests()
                .antMatchers("/api/courses/**").hasRole("JPT")
                .anyRequest().authenticated()
                .and().httpBasic()
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().addFilterBefore(new TestAuthFilter(AUTH_TOKEN_HEADER_NAME,AUTH_TOKEN,webClientBuilder), UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}
