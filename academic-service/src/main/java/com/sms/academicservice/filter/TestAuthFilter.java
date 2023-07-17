package com.sms.academicservice.filter;

import com.sms.response.SmsResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

public class TestAuthFilter extends OncePerRequestFilter {

    private static final Logger LOGGER= LoggerFactory.getLogger(TestAuthFilter.class);
    private String AUTH_TOKEN_HEADER_NAME;
    private String AUTH_TOKEN;
    private WebClient.Builder webClientBuilder;

    public TestAuthFilter(){}

    public TestAuthFilter(String AUTH_TOKEN_HEADER_NAME, String AUTH_TOKEN, WebClient.Builder webClientBuilder) {
        this.AUTH_TOKEN_HEADER_NAME = AUTH_TOKEN_HEADER_NAME;
        this.AUTH_TOKEN = AUTH_TOKEN;
        this.webClientBuilder=webClientBuilder;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
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
                LinkedHashMap<String,Object> userDetailsHashMap= (LinkedHashMap<String, Object>) res.getPayload();
                UserDetails userDetails=new UserDetails() {
                    @Override
                    public Collection<? extends GrantedAuthority> getAuthorities() {
                        List<SimpleGrantedAuthority> authorities= new ArrayList<>();
                        ((List<HashMap<String,String>>) userDetailsHashMap.get("authorities")).forEach(authority->{
                            authorities.add(new SimpleGrantedAuthority("ROLE_"+authority.get("authority")));
                        });
                        return authorities;
                    }

                    @Override
                    public String getPassword() {
                        return (String)userDetailsHashMap.get("password");
                    }

                    @Override
                    public String getUsername() {
                        return (String)userDetailsHashMap.get("username");
                    }

                    @Override
                    public boolean isAccountNonExpired() {
                        return (Boolean) userDetailsHashMap.get("accountNonExpired");
                    }

                    @Override
                    public boolean isAccountNonLocked() {
                        return (Boolean) userDetailsHashMap.get("accountNonLocked");
                    }

                    @Override
                    public boolean isCredentialsNonExpired() {
                        return (Boolean) userDetailsHashMap.get("credentialsNonExpired");
                    }

                    @Override
                    public boolean isEnabled() {
                        return (Boolean) userDetailsHashMap.get("enabled");
                    }
                };
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken=new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                filterChain.doFilter(request,response);
            }
        }catch (WebClientResponseException.BadRequest webClientResponseException){
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.setStatus(401);
            response.getWriter().write((new SmsResponse("Invalid Auth-Token",false,null)).toString());
        }

    }
}
